package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private static final String AUTHOR = "author";
    private static final String READER = "reader";
    private final PostRepository postRepository;
    private final ResponseRepository responseRepository;
    private final SubscriptionStandRepository subscriptionStandRepository;
    private final UserService userService;
    private final SpecialistRepository specialistRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private static final String AUTHOR = "author";
    private static final String READER = "reader";

    public List<StandCategoryDto> getAll() {
        List<Post> list = postRepository.findAll();
        list.sort(Comparator.comparing(post -> post.getCategory().getCategoryName()));
        List<StandCategoryDto> standPostList = new ArrayList<>();


        Map<Category, List<Post>> postsByCategory = new HashMap<>();
        for (Post post : list) {
            Category category = post.getCategory();
            if (postsByCategory.containsKey(category)) {
                postsByCategory.get(category).add(post);
            } else {
                List<Post> categoryPosts = new ArrayList<>();
                categoryPosts.add(post);
                postsByCategory.put(category, categoryPosts);
            }
        }
        for (Map.Entry<Category, List<Post>> entry : postsByCategory.entrySet()) {
            Category key = entry.getKey();
            List<Post> value = entry.getValue();
            List<PostDto> posts = value.stream().map(this::makeDtoFromPost).toList();
            standPostList.add(new StandCategoryDto(key.getCategoryName(), posts));
        }
        return standPostList;
    }

    private PostDto makeDtoFromPost(Post post) {
        List<Response> responseList = getAllPostResponses(post.getId());
        int newSpecialistNumber = 0;
        if (responseList != null) {
            HashSet<String> set = new HashSet<>();
            for (Response r :
                    responseList) {
                set.add(r.getConversationId());
            }
            newSpecialistNumber = set.size();
        }

        return PostDto.builder()
                .id(post.getId())
                .userPhoto(post.getUser().getPhoto())
                .userName(post.getUser().getUserName())
                .category(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .description(post.getDescription())
                .workRequiredTime(formatDateTime(post.getWorkRequiredTime()))
                .publishedDate(formatDateTime(post.getPublishedDate()))
                .responseNumber(newSpecialistNumber)
                .build();
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    private String formatDateTimeShort(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
        return dateTime.format(formatter);
    }

    public List<StandCategoryDto> getMySubscriptions(ViewerDto v) {
        List<SubscriptionStand> subscribed = subscriptionStandRepository.findAllBySpecialistId(v.getSpecialistId());
        List<StandCategoryDto> all = getAll();
        HashSet<StandCategoryDto> hashSet = new HashSet<>();
        for (SubscriptionStand c :
                subscribed) {
            for (StandCategoryDto s :
                    all) {
                if (c.getCategory().getCategoryName().equalsIgnoreCase(s.getCategory())) {
                    hashSet.add(s);
                }
            }
        }
        return new ArrayList<>(hashSet);
    }

    public List<StandCategoryDto> getOtherPosts(ViewerDto v) {
        List<SubscriptionStand> subscribed = subscriptionStandRepository.findAllBySpecialistId(v.getSpecialistId());
        List<StandCategoryDto> all = getAll();
        if (subscribed.isEmpty()) return all;
        HashSet<StandCategoryDto> hashSet = new HashSet<>();
        for (SubscriptionStand c :
                subscribed) {
            for (StandCategoryDto s :
                    all) {
                if (!c.getCategory().getCategoryName().equalsIgnoreCase(s.getCategory())) {
                    hashSet.add(s);
                }
            }
        }
        return new ArrayList<>(hashSet);
    }

    public PostDto getPostDto(Long postId) {
        var post = postRepository.findById(postId);
        return post.map(this::makeDtoFromPost).orElse(null);
    }

    public List<PostShortInfoDto> getSpecialistResponses(Long specialistId) {
        List<Response> list = responseRepository.findAllBySpecialistId(specialistId);
        HashSet<Long> set = new HashSet<>();
        for (Response r : list) {
            set.add(r.getPost().getId());
        }
        List<PostShortInfoDto> result = new ArrayList<>();
        for (Long l : set) {

            var post = postRepository.findById(l);
            if (post.isPresent()) {
                result.add(PostShortInfoDto.builder()
                        .postId(post.get().getId())
                        .postTitle(post.get().getTitle())
                        .build());
            }
        }
        return result;
    }

    public List<PostShortInfoDto> getCustomerPosts(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        List<PostShortInfoDto> list = new ArrayList<>();
        for (Post p :
                posts) {
            list.add(PostShortInfoDto.builder().postId(p.getId()).postTitle(p.getTitle()).build());
        }
        return list;
    }

    private void deleteUserNotification(User user, List<Notification> notifications) {
        if (!notifications.isEmpty()) {
            for (Notification n :
                    notifications) {
                if (n.getUser().getId().equals(user.getId())) {
                    notificationRepository.delete(n);
                }
            }
        }
    }

    private void sendNotification(String text, User user, String searchWord) {
        List<Notification> notifications = notificationRepository.findByNotificationTextContaining(searchWord);
        deleteUserNotification(user, notifications);
        notificationRepository.save(Notification.builder().user(user)
                .notificationText(new StringBuilder().append(text).append(" ").append(searchWord).append(".").toString())
                .notificationDate(LocalDateTime.now()).build());
    }

    public HttpStatus processResponse(Long postId, MessageDto response) {
        var post = postRepository.findById(postId);
        if (post.isEmpty()) return HttpStatus.NOT_FOUND;
        User user = userService.getUserFromSecurityContextHolder();
        String keyword = makeKeyword(post.get());
        if (user != null) {
            String conversationId = response.getViewer();
            var specialist = specialistRepository.findByUser(user);

            if (specialist.isEmpty()) {
                String specialistId = extractStringAfterDash(conversationId);
                var s = specialistRepository.findById(Long.parseLong(specialistId));
                if (s.isEmpty()) return HttpStatus.NOT_FOUND;

                User u = null;
                var userFromSpecialist = userRepository.findById(s.get().getUser().getId());
                if (userFromSpecialist.isPresent()) u = userFromSpecialist.get();

                responseRepository.save(Response.builder()
                        .post(post.get())
                        .user(user)
                        .conversationId(conversationId)
                        .response(response.getResponse())
                        .dateTime(LocalDateTime.now())
                        .build());
                sendNotification("Вы получили новое сообщение от автора запроса:", u, keyword);

            } else {
                User u = post.get().getUser();

                responseRepository.save(Response.builder()
                        .post(post.get())
                        .specialist(specialist.get())
                        .conversationId(conversationId)
                        .response(response.getResponse())
                        .dateTime(LocalDateTime.now())
                        .build());
                sendNotification("Вы получили новое сообщение в ответ на запрос:", u, keyword);
            }
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public String extractStringAfterDash(String input) {
        int index = input.indexOf("-");
        if (index != -1 && index < input.length() - 1) {
            return input.substring(index + 1);
        } else {
            return "";
        }
    }

    public String extractStringBeforeDash(String input) {
        int index = input.indexOf("-");
        if (index != -1) {
            return input.substring(0, index);
        } else {
            return input;
        }
    }

    public List<Response> getAllPostResponses(Long postId) {
        var post = postRepository.findById(postId);
        if (post.isEmpty()) return null;

        List<Response> responses = responseRepository.findAllByPostId(postId);
        if (responses.isEmpty()) return null;
        responses.sort(Comparator.comparing(Response::getDateTime));

        return responses;
    }

    public List<ResponseDto> getSpecialistConversation(Long postId, ViewerDto v) {
        String conversationId = new StringBuilder().append(postId).append("-").append(v.getSpecialistId()).toString();
        List<Response> conversationList = responseRepository.findAllByConversationId(conversationId);
        var specialist = specialistRepository.findById(v.getSpecialistId());
        if (specialist.isEmpty()) return null;

        var post = postRepository.findById(postId);
        if (post.isEmpty()) return null;
        List<ResponseDto> list = new ArrayList<>();
        for (Response r :
                conversationList) {
            String s = (r.getSpecialist() == null) ? READER : AUTHOR;
            list.add(ResponseDto.builder()
                    .response(r.getResponse())
                    .viewer(s)
                    .dateTime(formatDateTimeShort(r.getDateTime()))
                    .build());
        }
        return list;
    }

    public List<ConversationDto> getCustomerConversations(Long postId) {
        List<Response> responses = getAllPostResponses(postId);
        if (responses == null) {
            return null;
        }
        responses.sort(Comparator.comparing(Response::getConversationId));
        HashSet<String> uniqueConversationIds = new HashSet<>();
        for (Response r : responses) {
            uniqueConversationIds.add(r.getConversationId());
        }

        Map<String, List<ResponseDto>> conversationDtoList = new HashMap<>();
        for (String s : uniqueConversationIds) {
            conversationDtoList.put(s, new ArrayList<>());
        }
        for (Response r : responses) {
            for (Map.Entry<String, List<ResponseDto>> entry : conversationDtoList.entrySet()) {
                String key = entry.getKey();
                List<ResponseDto> value = entry.getValue();
                String s = (r.getSpecialist() == null) ? AUTHOR : READER;

                if (r.getConversationId().equalsIgnoreCase(key)) {
                    value.add(ResponseDto.builder()
                            .response(r.getResponse())
                            .viewer(s)
                            .dateTime(formatDateTimeShort(r.getDateTime()))
                            .build());
                }
            }
        }
        List<ConversationDto> list = new ArrayList<>();
        for (Map.Entry<String, List<ResponseDto>> entry : conversationDtoList.entrySet()) {
            list.add(ConversationDto.builder()
                    .conversationId(entry.getKey())
                    .messages(entry.getValue())
                    .build());
        }
        return list;
    }

    public void createNewPost(PostInputDto dto) {
        User user = userService.getUserFromSecurityContextHolder();
        if (user != null) {
            var c = categoryRepository.findById(dto.getCategoryId());
            c.ifPresent(category -> postRepository.saveAndFlush(Post.builder()
                    .user(user)
                    .category(category)
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .workRequiredTime(dto.getWorkRequiredTime())
                    .publishedDate(LocalDateTime.now())
                    .build()));

            var newPost = postRepository.findByTitleAndWorkRequiredTime(dto.getTitle(), dto.getWorkRequiredTime());
            if (newPost.isPresent()) {
                String keyword = makeKeyword(newPost.get());
                String text = new StringBuilder().append("Создана новая запись на стенде в категории ").append(c.get().getCategoryName()).append(":").toString();
                List<SubscriptionStand> subscriptionsOnCategory = subscriptionStandRepository.findByCategory_Id(dto.getCategoryId());
                for (SubscriptionStand s : subscriptionsOnCategory) {
                    sendNotification(text, s.getSpecialist().getUser(), keyword);
                }
            }
        }


    }

    private Notification makeNotificationToSpecialistBySubscriptionOnCategory(Specialist specialist, Category category) {
        return Notification.builder()
                .user(specialist.getUser())
                .notificationText("Создана новая запись на стенде в категории: " + category.getCategoryName())
                .notificationDate(LocalDateTime.now())
                .build();
    }

    public List<PostDto> getCustomerPostDtos(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        if (posts.isEmpty()) return null;
        return posts.stream().map(this::makeDtoFromPost).toList();
    }

    public Long getPostByConversationId(String conversationId) {
        return Long.parseLong(extractStringBeforeDash(conversationId));
    }

    public List<ResponseDto> getCustomerMsgByConversation(String conversationId) {
        List<Response> responses = responseRepository.findAllByConversationId(conversationId);
        List<ResponseDto> list = new ArrayList<>();
        for (Response r : responses) {
            String s = (r.getSpecialist() == null) ? AUTHOR : READER;
            list.add(ResponseDto.builder()
                    .response(r.getResponse())
                    .viewer(s)
                    .dateTime(formatDateTimeShort(r.getDateTime()))
                    .build()
            );
        }
        return list;
    }
private String makeKeyword(Post post) {
        return new StringBuilder().append(post.getTitle()).append(" (")
                .append(post.getPublishedDate()).append(")").toString();
}
    @Transactional
    public void selectSpecialist(String conversationId) {
        List<Response> responses = responseRepository.findAllByConversationId(conversationId);
        Post post = responses.get(0).getPost();
        String keyword = makeKeyword(post);
        String successText = String.format("Пользователь выбрал Ваш отклик, чтобы воспользоваться Вашими услугами. Если возникнут вопросы, Вы можете отправить сообщение %s в разделе 'Сообщения'.", post.getUser().getUserName());
        String declineText = "К сожалению, пользователь не выбрал Ваш отклик. Данный запрос был удален из стенда:";

        var user = userService.getUserFromSecurityContextHolder();
        Long specialistId = Long.parseLong(extractStringAfterDash(conversationId));
        var specialistSuccess = specialistRepository.findById(specialistId);
        if (specialistSuccess.isPresent()) {
            sendNotification(successText, specialistSuccess.get().getUser(), keyword);
            responseRepository.deleteAllByConversationId(conversationId);
        }

        String userText = String.format("Вы выбрали отклик от специалиста %s. Если возникнут вопросы, Вы можете отправить сообщение %s в разделе 'Сообщения'.", specialistSuccess.get().getCompanyName(), specialistSuccess.get().getCompanyName());
        sendNotification(userText, user, keyword);


    private String makeKeyword(Post post) {
        return new StringBuilder().append(post.getTitle()).append(" (")
                .append(post.getPublishedDate()).append(")").toString();
    }

    @Transactional
    public void selectSpecialist(String conversationId) {
        List<Response> responses = responseRepository.findAllByConversationId(conversationId);
        Post post = responses.get(0).getPost();
        String keyword = makeKeyword(post);
        String successText = String.format("Пользователь выбрал Ваш отклик, чтобы воспользоваться Вашими услугами. Если возникнут вопросы, Вы можете отправить сообщение %s в разделе 'Сообщения'.", post.getUser().getUserName());
        String declineText = "К сожалению, пользователь не выбрал Ваш отклик. Данный запрос был удален из стенда:";

        var user = userService.getUserFromSecurityContextHolder();
        Long specialistId = Long.parseLong(extractStringAfterDash(conversationId));
        var specialistSuccess = specialistRepository.findById(specialistId);
        if (specialistSuccess.isPresent()) {
            sendNotification(successText, specialistSuccess.get().getUser(), keyword);
            responseRepository.deleteAllByConversationId(conversationId);
        }

        String userText = String.format("Вы выбрали отклик от специалиста %s. Если возникнут вопросы, Вы можете отправить сообщение %s в разделе 'Сообщения'.", specialistSuccess.get().getCompanyName(), specialistSuccess.get().getCompanyName());
        sendNotification(userText, user, keyword);

        List<Response> responseList = responseRepository.findAllByPostId(post.getId());
        Set<Long> specialistIds = new HashSet<>();
        for (Response r : responseList) {
            if (r.getSpecialist() != null) specialistIds.add(r.getSpecialist().getId());
        }
        specialistIds.remove(specialistId);
        if (!specialistIds.isEmpty()) {
            for (Long l : specialistIds) {
                var specialistDecline = specialistRepository.findById(l);
                sendNotification(declineText, specialistDecline.get().getUser(), keyword);
                responseRepository.deleteAllByConversationId(conversationId);
            }
        }
        postRepository.delete(post);

    }

    @Transactional
    public void deletePost(Long postId) {
        var post = postRepository.findById(postId);
        if (post.isPresent()) {
            postRepository.deleteById(postId);
        }
        String keyword = makeKeyword(post.get());

        List<Response> responses = responseRepository.findAllByPostId(postId);
        if(responses.size() > 0) {
            Set<Long> specialistIds = new HashSet<>();
            for (Response r : responses) {
                if (r.getSpecialist() != null) specialistIds.add(r.getSpecialist().getId());
            }
            String notificationText = "Пользователь удалил запрос, на который Вы откликнулись:";
            for (Long l:
                 specialistIds) {
                for (Response r:
                     responses) {
                    if(l.equals(r.getSpecialist().getId())) {
                        sendNotification(notificationText, r.getSpecialist().getUser(), keyword);
                    }
                }
            }

        }
        List<Notification> notifications = notificationRepository.findByNotificationTextContaining(keyword);
        for (Notification n:
                notifications) {
            notificationRepository.delete(n);
        }
    }
}
