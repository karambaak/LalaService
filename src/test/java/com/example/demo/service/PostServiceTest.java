package com.example.demo.service;

import com.example.demo.dto.StandCategoryDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.SubscriptionStand;
import com.example.demo.entities.User;
import io.cucumber.java.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_get_list_of_posts_grouped_by_category() {
        List<StandCategoryDto> testList = postService.getAllStandCategoryDtos(allPosts());
        assertEquals(3, testList.size());
    }

    @Test
    void should_get_Empty_List() {
        List<Post> emptyList = new ArrayList<>();
        List<StandCategoryDto> testList = postService.getAllStandCategoryDtos(emptyList);
        assertTrue(testList.isEmpty());
    }

    @Test
    void should_get_list_of_posts_subscribed_by_specialist() {
        List<SubscriptionStand> subscribed = new ArrayList<>();
        SubscriptionStand s = new SubscriptionStand();
        s.setCategory(mockCategory());
        subscribed.add(s);

        List<StandCategoryDto> all = postService.getAllStandCategoryDtos(allPosts());

        List<StandCategoryDto> testList = postService.getAllBySubsctiontionStandList(subscribed, all);
        assertEquals(2, testList.size());
    }

    @Test
    void should_get_list_of_posts_not_subscribed_by_specialist() {
        List<SubscriptionStand> subscribed = new ArrayList<>();
        SubscriptionStand s = new SubscriptionStand();
        s.setCategory(mockCategory());
        subscribed.add(s);

        List<StandCategoryDto> all = postService.getAllStandCategoryDtos(allPosts());

        List<StandCategoryDto> testList = postService.getPostsNotSubscribed(subscribed, all);
        assertEquals(1, testList.size());
    }

    @Test
    void formatMatch_LocalDateTime_conversionToString() {
        LocalDateTime time = LocalDateTime.of(2023, Month.NOVEMBER, 21, 21, 0);
        String expectedResult = "2023-11-21 21:00";
        String actualResult = postService.formatDateTime(time);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void anotherFormatMatch_LocalDateTime_conversionToString() {
        LocalDateTime time = LocalDateTime.of(2023, Month.NOVEMBER, 21, 21, 0);
        String expectedResult = "11/21 21:00";
        String actualResult = postService.formatDateTimeShort(time);
        assertEquals(expectedResult, actualResult);
    }

    private User mockUser() {
        User user = new User();
        user.setId(2L);
        user.setPhoneNumber("1234");
        user.setPassword("qwe");
        user.setEnabled(true);
        user.setUserType("CUSTOMER");
        return user;
    }

    private Category mockCategory() {
        Category c = new Category();
        c.setCategoryName("Test1");
        return c;
    }

    private Category mockAnotherCategory() {
        Category c = new Category();
        c.setCategoryName("Test2");
        return c;
    }

    public List<Post> allPosts() {
        List<Post> list = new ArrayList<>();
        Post p1 = new Post();
        p1.setId(1L);
        p1.setCategory(mockCategory());
        p1.setUser(mockUser());
        p1.setTitle("Test post 1");
        p1.setWorkRequiredTime(LocalDateTime.of(2023, Month.DECEMBER, 1, 12, 0));
        p1.setPublishedDate(LocalDateTime.of(2023, Month.NOVEMBER, 21, 21, 0));

        Post p2 = new Post();
        p2.setId(2L);
        p2.setCategory(mockCategory());
        p2.setUser(mockUser());
        p2.setTitle("Test post 2");
        p2.setWorkRequiredTime(LocalDateTime.of(2023, Month.DECEMBER, 1, 12, 0));
        p2.setPublishedDate(LocalDateTime.of(2023, Month.NOVEMBER, 21, 22, 0));

        Post p3 = new Post();
        p3.setId(3L);
        p3.setCategory(mockAnotherCategory());
        p3.setUser(mockUser());
        p3.setTitle("Test post 3");
        p3.setWorkRequiredTime(LocalDateTime.of(2023, Month.DECEMBER, 1, 12, 0));
        p3.setPublishedDate(LocalDateTime.of(2023, Month.NOVEMBER, 21, 23, 0));

        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }
}
