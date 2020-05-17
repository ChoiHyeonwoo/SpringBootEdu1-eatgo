package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

// TDD (Test Code 작성)
// Red (실패하는 테스트) -> Green (성공하는 테스트) -> Refactoring(테스트 코드에 대한 리펙토링)
// 위 작업을 계속 반복하면서 원하는 기능 구현, 새로운 기능을 발견, 잠재적인 문제를 발견하여 올바르게 작동하는 코드를 작성할 수 있음.
public class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");

        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }
    @Test
    public void information (){
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");

        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }

}