package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.repository.*;
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
    private final PostRepository postRepository;
    private final ResponseRepository responseRepository;
    private final SubscriptionStandRepository subscriptionStandRepository;
    private final UserService userService;
    private final SpecialistRepository specialistRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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


    public HttpStatus processResponse(Long postId, ResponseDto response) {
        var post = postRepository.findById(postId);
        if (post.isEmpty()) return HttpStatus.NOT_FOUND;
        User user = userService.getUserFromSecurityContextHolder();
        String keyword = new StringBuilder().append(post.get().getTitle()).append(" (")
                .append(post.get().getPublishedDate()).append(")").toString();
        if (user != null) {
            List<Notification> notifications = notificationRepository.findByNotificationTextContaining(keyword);

            String conversationId = response.getViewer();
            var specialist = specialistRepository.findByUser(user);

            if (specialist.isEmpty()) {
                String specialistId = extractStringAfterDash(conversationId);
                var s = specialistRepository.findById(Long.parseLong(specialistId));
                if (s.isEmpty()) return HttpStatus.NOT_FOUND;

                User u = null;
                var userFromSpecialist = userRepository.findById(s.get().getUser().getId());
                if (userFromSpecialist.isPresent()) u = userFromSpecialist.get();

                deleteUserNotification(u, notifications);

                responseRepository.save(Response.builder()
                        .post(post.get())
                        .user(user)
                        .conversationId(conversationId)
                        .response(response.getResponse())
                        .dateTime(LocalDateTime.now())
                        .build());
                notificationRepository.save(Notification.builder().user(u)
                        .notificationText(String.format("Вы получили новое сообщение от автора запроса: %s (%s).", post.get().getTitle(), post.get().getPublishedDate()))
                        .notificationDate(LocalDateTime.now()).build());
            } else {
                User u = post.get().getUser();
                deleteUserNotification(u, notifications);

                responseRepository.save(Response.builder()
                        .post(post.get())
                        .specialist(specialist.get())
                        .conversationId(conversationId)
                        .response(response.getResponse())
                        .dateTime(LocalDateTime.now())
                        .build());
                notificationRepository.save(Notification.builder().user(u)
                        .notificationText(String.format("Вы получили новое сообщение в ответ на запрос: %s (%s).", post.get().getTitle(), post.get().getPublishedDate()))
                        .notificationDate(LocalDateTime.now()).build());

            }
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    private String extractStringAfterDash(String input) {
        int index = input.indexOf("-");
        if (index != -1 && index < input.length() - 1) {
            return input.substring(index + 1);
        } else {
            return "";
        }
    }


    private List<Response> getAllPostResponses(Long postId) {
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
            String s = (r.getSpecialist() == null) ? "reader" : "author";
            list.add(ResponseDto.builder()
                    .response(r.getResponse())
                    .viewer(s)
                    .build());
        }
        return list;
    }

    public List<ConversationDto> getCustomerConversations(Long postId) {
        List<Response> responses = responseRepository.findAllByPostId(postId);
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
                String s = (r.getSpecialist() == null) ? "author" : "reader";

                if (r.getConversationId().equalsIgnoreCase(key)) {
                    value.add(ResponseDto.builder()
                            .response(r.getResponse())
                            .viewer(s)
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
            if (c.isPresent()) {
                postRepository.save(Post.builder()
                        .user(user)
                        .category(c.get())
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .workRequiredTime(dto.getWorkRequiredTime())
                        .publishedDate(LocalDateTime.now())
                        .build());
            }
        }
    }

    public List<PostDto> getCustomerPostDtos(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        if(posts.isEmpty()) return null;
        return posts.stream().map(this::makeDtoFromPost).toList();
    }
}
