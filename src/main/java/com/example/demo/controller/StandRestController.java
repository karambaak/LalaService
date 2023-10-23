package com.example.demo.controller;

import com.example.demo.entities.Notification;
import com.example.demo.entities.Response;
import com.example.demo.entities.Specialist;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ResponseRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/stand")
@RequiredArgsConstructor
public class StandRestController {
    private final ResponseRepository responseRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final NotificationRepository notificationRepository;

    @PostMapping("/response/{postId}")
    public HttpStatus respond(@PathVariable Long postId, @RequestBody String responseText) {
        var post = postRepository.findById(postId);
        if (post.isEmpty()) {

            return HttpStatus.NOT_FOUND;
        } else {

            Specialist specialist = userService.getSpecialistFromSecurityContextHolder();
            if (specialist == null) return HttpStatus.NOT_FOUND;

            var isNewResponse = responseRepository.findByPostAndSpecialist(post.get(), specialist);

            responseRepository.save(Response.builder()
                    .post(post.get())
                    .specialist(specialist)
                    .response(responseText)
                    .dateTime(LocalDateTime.now())
                    .build()
            );

            if (isNewResponse.isEmpty()) {

                String companyName = specialist.getCompanyName();
                if (companyName == null) companyName = specialist.getId().toString();
                var customer = post.get().getUser();

                notificationRepository.save(Notification.builder()
                        .user(customer)
                        .notificationText(String.format("Есть новый отклик на Ваш запрос на стенде от %s", companyName))
                        .notificationDate(LocalDateTime.now())
                        .build());

            }
        }

        return HttpStatus.OK;
    }
}
