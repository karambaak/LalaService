package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<PostDto> getAll() {
        List<Post> list = postRepository.findAll();
        return list.stream().map(this::makeDtoFromPost).toList();
    }
    private PostDto makeDtoFromPost(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .userName(post.getUser().getUserName())
                .category(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .description(post.getDescription())
                .workRequiredTime(post.getWorkRequiredTime())
                .publishedDate(post.getPublishedDate())
                .build();
    }
}
