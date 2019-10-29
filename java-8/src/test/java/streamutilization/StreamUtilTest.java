package streamutilization;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import streamex.Dish;
import streamex.Dish.Type;
import streamex.Dishes;

@DisplayName("스트림 활용")
public class StreamUtilTest {


    @Test
    @Order(1)
    @DisplayName("스트림 필터링, 슬라이싱")
    void filterAndSlice() {
        List<Dish> dishes = Dishes.getDefault()
            .stream()
            .filter(d -> Type.MEAT == d.getType())
            .limit(2)
            .collect(Collectors.toList());

        assertThat(dishes.size()).isEqualTo(2);
        assertThat(dishes.get(1).getName()).isEqualTo("beef");
    }

    @Test
    @Order(2)
    @DisplayName("스트림 매핑 - 제곱근")
    void map() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> results = integers.stream()
            .map(i -> i * i)
            .collect(Collectors.toList());

        assertThat(results.get(0)).isEqualTo(1);
        assertThat(results.get(4)).isEqualTo(25);
    }

    @Test
    @Order(3)
    @DisplayName("스트림 평면화")
    public void flatMap() {
        // result => [(1,3), (1,4), (2,3), (2,4), ...]
        List<Integer> inputA = Arrays.asList(1, 2, 3);
        List<Integer> inputB = Arrays.asList(3, 4);
        List<Pair<Integer, Integer>> results = inputA.stream()
            .flatMap(a -> inputB.stream()
                .map(b -> new Pair<>(a, b)))
            .collect(Collectors.toList());

        assertThat(results.get(1).getKey()).isEqualTo(1);
        assertThat(results.get(1).getValue()).isEqualTo(4);

        results = inputA.stream()
            .flatMap(a -> inputB.stream()
                .filter(b -> (a + b) % 3 == 0)
                .map(b -> new Pair<>(a, b)))
            .collect(Collectors.toList());

        assertThat(results.get(0).getKey()).isEqualTo(2);
        assertThat(results.get(0).getValue()).isEqualTo(4);
    }

    @Test
    @Order(4)
    @DisplayName("검색과 매칭")
    void match() {
        List<Dish> dishes = Dishes.getDefault();

        boolean isHealthy = dishes.stream().allMatch(d -> d.getCalories() < 1000);
        assertTrue(isHealthy);

        boolean hasVegetarian = dishes.stream().anyMatch(Dish::getVegetarian);
        assertTrue(hasVegetarian);

        isHealthy = dishes.stream().noneMatch(d -> d.getCalories() > 1000);
        assertTrue(isHealthy);

        Optional<Dish> randomDish = dishes.stream()
            .filter(Dish::getVegetarian)
            .findAny();
        assertTrue(randomDish.orElse(Dish.builder().vegetarian(true).build()).getVegetarian());

        dishes.stream()
            .filter(Dish::getVegetarian)
            .findAny()
            .ifPresent(d -> System.out.println(d.getName()));
    }

    @Test
    @Order(5)
    @DisplayName("리듀싱")
    void reduce() {
        // 스트림 총합
        Integer sum = Stream.of(1, 3, 5, 7)
            .reduce(0, Integer::sum);
        assertThat(sum).isEqualTo(16);

        Optional<Integer> result = Stream.of(1, 3, 5, 7)
            .reduce(Integer::sum);
        assertThat(result.orElse(0)).isEqualTo(16);

        // 리듀스 매커니즘을 활용해서 최댓값 최솟값 찾기
        result = Stream.of(1, 5, 3, 2)
            .reduce(Integer::max);
        assertThat(result.orElse(0)).isEqualTo(5);

        result = Stream.of(1, 5, 6, 3, 3, 4, 5)
            .reduce(Integer::min);
        assertThat(result.orElse(0)).isEqualTo(1);

        // Stream의 min max 이용하기
        result = Stream.of(1, 5, 3, 2)
            .max(Comparator.comparing(Function.identity()));
        assertThat(result.orElse(0)).isEqualTo(5);

        result = Stream.of(1, 5, 3, 2)
            .min(Comparator.comparing(Function.identity()));
        assertThat(result.orElse(0)).isEqualTo(1);

        // map 과 reduce 매커니즘 이용해서 리스트 갯수 구하기
        Integer count = Dishes.getDefault().stream()
            .map(d -> 1)
            .reduce(0, Integer::sum);
        assertThat(count).isEqualTo(9);
    }
}
