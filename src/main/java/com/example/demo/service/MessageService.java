package com.example.demo.service;

import com.example.demo.dto.MessageBundleDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entities.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final NotificationRepository notificationRepository;
    private final PostRepository postRepository;
    private final ResponseRepository responseRepository;
    private final SpecialistRepository specialistRepository;
    private final UserRepository userRepository;
    private final SpecialistService specialistService;
    private final PostService postService;

    public List<MessageBundleDto> getAll() {
        User user = userService.getUserFromSecurityContextHolder();
        if (user != null) {
            List<MessageBundleDto> list = getMessages(user);
            list.addAll(getResponses(user));
            return list;
        }
        return Collections.emptyList();
    }


    private List<MessageBundleDto> getMessages(User user) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> shortList = new ArrayList<>();
        shortList.addAll(iAmReceiver(allMessages, user));
        shortList.addAll(iAmSender(allMessages, user));
        shortList.sort(Comparator.comparing(Message::getDateTime).reversed());
        Set<Long> uniqueUsers = uniqueIds(shortList, user);
        return filterBundle(shortList, uniqueUsers);

    }

    List<MessageBundleDto> filterBundle(List<Message> messages, Set<Long> filter) {
        List<MessageBundleDto> list = new ArrayList<>();
        for (Long l : filter)
            for (Message m : messages) {
                if (m.getSender().getId().equals(l)) {
                    list.add(MessageBundleDto.builder()
                            .id(new StringBuilder().append("msg").append("-").append(m.getSender().getId()).toString())
                            .senderPhoto(m.getSender().getPhoto())
                            .senderName(m.getSender().getUserName())
                            .lastMessageText(m.getMessageText())
                            .lastMessageDateTime(formatDateTime(m.getDateTime()))
                            .build());
                    break;

                } else if (m.getReceiver().getId().equals(l)) {
                    list.add(MessageBundleDto.builder()
                            .id(new StringBuilder().append("msg").append("-").append(m.getReceiver().getId()).toString())
                            .senderPhoto(m.getReceiver().getPhoto())
                            .senderName(m.getReceiver().getUserName())
                            .lastMessageText(m.getMessageText())
                            .lastMessageDateTime(formatDateTime(m.getDateTime()))
                            .build());
                    break;
                }

            }
        return list;
    }

    private Set<Long> uniqueIds(List<Message> messages, User user) {
        return messages.stream()
                .flatMap(m -> Stream.of(m.getReceiver().getId(), m.getSender().getId()))
                .filter(id -> !id.equals(user.getId()))
                .collect(Collectors.toSet());
    }

    private List<Message> iAmSender(List<Message> messages, User user) {
        return messages.stream()
                .filter(m -> m.getSender().getId().equals(user.getId()))
                .toList();
    }

    private List<Message> iAmReceiver(List<Message> messages, User user) {
        return messages.stream()
                .filter(m -> m.getReceiver().getId().equals(user.getId()))
                .toList();
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
        return dateTime.format(formatter);
    }


    public List<NotificationDto> getAllNotifications() {
        User user = userService.getUserFromSecurityContextHolder();
        if (user != null) {
            List<Notification> list = notificationRepository.findAllByUser(user);
            return list.stream().map(this::makeDtoFromNotification).toList();
        }
        return Collections.emptyList();
    }

    private NotificationDto makeDtoFromNotification(Notification n) {
        return NotificationDto.builder()
                .id(n.getId())
                .userId(n.getUser().getId())
                .notificationText(n.getNotificationText())
                .notificationDate(n.getNotificationDate())
                .build();
    }

    private List<MessageBundleDto> getResponses(User user) {
        List<Response> allResponses = responseRepository.findAll();
        if (user.getUserType().equalsIgnoreCase("customer")) {
            List<Post> posts = getAllCustomerPosts(user);
            List<Response> responses = getCustomerResponses(allResponses, posts);
            Set<String> filter = uniqueConversations(responses);
            return customerResponses(responses, filter);
        } else {
            Set<String> filter = uniqueConversationsBySpecialist(allResponses, user);
            List<Response> responses = getSpecialistResponses(allResponses, filter);
            return specialistResponses(responses, filter);
        }
    }

    private List<Post> getAllCustomerPosts(User user) {
        List<Post> list = postRepository.findAllByUserId(user.getId());
        if (list.isEmpty()) return Collections.emptyList();
        return list;
    }

    private List<Response> getCustomerResponses(List<Response> responses, List<Post> posts) {
        List<Response> list = new ArrayList<>();
        for (Post p : posts) {
            for (Response r : responses) {
                if (r.getPost().equals(p)) list.add(r);
            }
        }
        return list;
    }

    private Set<String> uniqueConversations(List<Response> responses) {
        return responses.stream()
                .map(Response::getConversationId)
                .collect(Collectors.toSet());
    }

    private List<MessageBundleDto> customerResponses(List<Response> responses, Set<String> filter) {
        List<MessageBundleDto> list = new ArrayList<>();
        responses.sort(Comparator.comparing(Response::getDateTime).reversed());
        for (String s : filter) {
            for (Response r : responses) {
                if (r.getConversationId().equalsIgnoreCase(s) && r.getSpecialist() != null) {
                    var user = userRepository.findById(r.getSpecialist().getUser().getId());
                    if (user.isPresent()) {
                        list.add(MessageBundleDto.builder()
                                .id(new StringBuilder().append("response").append("-").append(s).toString())
                                .senderPhoto(user.get().getPhoto())
                                .senderName(new StringBuilder().append("[стенд] ").append(specialistService.findSpecialistName(r.getSpecialist())).toString())
                                .lastMessageText(getLastMessageText(responses, s))
                                .lastMessageDateTime(getLastMessageDateTime(responses, s))
                                .build());
                        break;
                    }
                }
            }
        }
        return list;
    }

    private String getLastMessageText(List<Response> responses, String conversationId) {
        return responses.stream()
                .filter(r -> r.getConversationId().equalsIgnoreCase(conversationId))
                .map(Response::getResponse)
                .findFirst()
                .orElse(null);
    }

    private String getLastMessageDateTime(List<Response> responses, String conversationId) {
        LocalDateTime dt = responses.stream()
                .filter(r -> r.getConversationId().equalsIgnoreCase(conversationId))
                .map(Response::getDateTime)
                .findFirst()
                .orElse(null);
        if (dt == null) return null;
        return formatDateTime(dt);
    }

    private Set<String> uniqueConversationsBySpecialist(List<Response> responses, User user) {
        var specialist = specialistRepository.findByUser(user);
        Set<String> set = new HashSet<>();
        if (specialist.isPresent()) {
            for (Response r : responses) {
                if (r.getSpecialist() != null && (r.getSpecialist().getId().equals(specialist.get().getId()))) {
                    set.add(r.getConversationId());

                }
            }
        }
        return set;
    }

    private List<Response> getSpecialistResponses(List<Response> responses, Set<String> filter) {
        List<Response> list = new ArrayList<>();
        for (String s : filter) {
            for (Response r : responses) {
                if (s.equalsIgnoreCase(r.getConversationId())) list.add(r);
            }
        }
        return list;
    }

    private List<MessageBundleDto> specialistResponses(List<Response> responses, Set<String> filter) {
        List<MessageBundleDto> list = new ArrayList<>();
        responses.sort(Comparator.comparing(Response::getDateTime).reversed());
        for (String s : filter) {
            for (Response r : responses) {
                if (r.getConversationId().equalsIgnoreCase(s) && r.getSpecialist() == null) {
                    list.add(MessageBundleDto.builder()
                            .id(new StringBuilder().append("response").append("-").append(s).toString())
                            .senderPhoto(r.getUser().getPhoto())
                            .senderName(new StringBuilder().append("[стенд] ").append(r.getUser().getUserName()).toString())
                            .lastMessageText(getLastMessageText(responses, s))
                            .lastMessageDateTime(getLastMessageDateTime(responses, s))
                            .build());
                    break;
                }
            }
        }
        return list;
    }

    public List<ResponseDto> viewMessages(String id) {
        User user = userService.getUserFromSecurityContextHolder();
        if (user == null) return Collections.emptyList();
        Long value = Long.parseLong(postService.extractStringAfterDash(id));
        List<ResponseDto> list = new ArrayList<>();
        List<Message> messages = messageRepository.findBySenderIdAndReceiverId(value, user.getId());
        messages.addAll(messageRepository.findBySenderIdAndReceiverId(user.getId(), value));
        messages.sort(Comparator.comparing(Message::getDateTime));
        for (Message m : messages) {
            String s = (user.getId().equals(m.getSender().getId())) ? "author" : "reader";

            list.add(ResponseDto.builder()
                    .response(m.getMessageText())
                    .viewer(s)
                    .dateTime(formatDateTime(m.getDateTime()))
                    .build());
        }
        return list;

    }

    public HttpStatus saveMessage(String msgId, MessageDto responseDto) {
        User user = userService.getUserFromSecurityContextHolder();
        Long receiverId = Long.parseLong(postService.extractStringAfterDash(msgId));
        var user2 = userRepository.findById(receiverId);
        if (user2.isPresent()) {
            messageRepository.save(Message.builder()
                    .sender(user)
                    .receiver(user2.get())
                    .messageText(responseDto.getResponse())
                    .dateTime(LocalDateTime.now())
                    .build());
        } else {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }

    public HttpStatus saveNewMessage(MessageDto messageDto) {
        User sender = userService.getUserFromSecurityContextHolder();
        if (sender != null) {
            User receiver = null;
            try {
                Long id = Long.parseLong(messageDto.getViewer());
                var user = userRepository.findById(id);
                if (!user.isEmpty()) receiver = user.get();
            } catch (NumberFormatException e) {
                var user = userRepository.findAllByUserNameContainingIgnoreCase(messageDto.getViewer());
                if (user.size() == 1) {
                    receiver = user.get(0);
                } else {
                    return HttpStatus.NOT_FOUND;
                }
            }
            messageRepository.save(Message.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .messageText(messageDto.getResponse())
                    .dateTime(LocalDateTime.now())
                    .build());
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Transactional
    public void deleteNotification(long notificationId, long userId) {
        notificationRepository.deleteNotificationByIdAndUserId(notificationId, userId);
    }
}

