package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository(){
        restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L,"Cyber Food", "Seoul"));
    }

    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(Long id) {
        // List.stream() java 8 부터 추가
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }
}
