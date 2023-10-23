package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.StandCategoryDto;
import com.example.demo.dto.ViewerDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.Response;
import com.example.demo.entities.SubscriptionStand;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ResponseRepository;
import com.example.demo.repository.SubscriptionStandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        List<Response> responseList = responseRepository.findAllByPostId(post.getId());
        return PostDto.builder()
                .id(post.getId())
                .userPhoto(post.getUser().getPhoto())
                .userName(post.getUser().getUserName())
                .category(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .description(post.getDescription())
                .workRequiredTime(formatDateTime(post.getWorkRequiredTime()))
                .publishedDate(formatDateTime(post.getPublishedDate()))
                .responseNumber(responseList.size())
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

        for (SubscriptionStand c:
             subscribed) {
            for (StandCategoryDto s:
                 all) {
                if(c.getCategory().getCategoryName().equalsIgnoreCase(s.getCategory())) {
                    hashSet.add(s);
                }
            }
        }
        return new ArrayList<>(hashSet);
    }

    public List<StandCategoryDto> getOtherPosts(ViewerDto v) {
        List<SubscriptionStand> subscribed = subscriptionStandRepository.findAllBySpecialistId(v.getSpecialistId());
        List<StandCategoryDto> all = getAll();
        HashSet<StandCategoryDto> hashSet = new HashSet<>();
        for (SubscriptionStand c:
                subscribed) {
            for (StandCategoryDto s:
                    all) {
                if(!c.getCategory().getCategoryName().equalsIgnoreCase(s.getCategory())) {
                    hashSet.add(s);
                }
            }
        }
        return new ArrayList<>(hashSet);
    }

    public PostDto getPostDto(Long postId) {
        var post = postRepository.findById(postId);
        if(post.isPresent()) {
            return makeDtoFromPost(post.get());
        }else {
            return null;
        }
    }
}
