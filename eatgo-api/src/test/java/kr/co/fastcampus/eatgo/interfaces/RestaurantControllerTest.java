package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // spring boot 2.1 버전 테스트 -> 2.2는 또 다름
@WebMvcTest(RestaurantController.class) // RestaurantController를 테스트
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean //테스트를 위한 가짜
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
        .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ));

    }
    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                                .id(1004L)
                                .name("JOKER House")
                                .address("Seoul")
                                .build();
        Restaurant restaurant2 = Restaurant.builder()
                                .id(2020L)
                                .name("Cyber Food")
                                .address("Seoul")
                                .build();

        restaurant1.setMenuItem(Arrays.asList(MenuItem.builder()
                                    .name("Kimchi")
                                    .build()));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Beryong\", \"address\":\"busan\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/restaurants/1234"))
            .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER Bar\", \"address\":\"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
    }

}