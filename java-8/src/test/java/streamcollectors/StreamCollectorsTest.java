package streamcollectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import streamex.Dish;
import streamex.Dishes;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Collectors 클래스의 정적 팩토리 메서드 종합 선물 세트")
class StreamCollectorsTest {

    @Test
    @DisplayName("toList")
    void toList() {
        List<Dish> dishes = Dishes.getDefault()
            .stream()
            .filter(Dish::getVegetarian)
            .collect(Collectors.toList());
    }

    @Test
    @DisplayName("toSet")
    void toSet() {
        Set<Dish> dishSet = Dishes.getDefault()
            .stream()
            .filter(Dish::getVegetarian)
            .collect(Collectors.toSet());
    }

    @Test
    @DisplayName("counting")
    void count() {
        long howManyDishes = Dishes.getDefault()
            .stream()
            .filter(Dish::getVegetarian)
//            .collect(Collectors.counting());
            .count();
    }

    @Test
    @DisplayName("summingInt")
    void summingInt() {
        int totalCalories = Dishes.getDefault()
            .stream()
            .collect(Collectors.summingInt(Dish::getCalories));

        int totalCalories2 = Dishes.getDefault()
            .stream()
            .mapToInt(Dish::getCalories)
            .sum();
    }

    @Test
    @DisplayName("averagingInt")
    void averagingInt() {
        Double avgCalories = Dishes.getDefault()
            .stream()
            .collect(Collectors.averagingInt(Dish::getCalories));
    }

    @Test
    @DisplayName("summarizingInt - 최댓값, 최솟값, 합계, 평균 등의 정수 정보 통계 수집")
    void summarizingInt() {
        IntSummaryStatistics dishStatistics = Dishes.getDefault()
            .stream()
            .collect(Collectors.summarizingInt(Dish::getCalories));

        assertThat(dishStatistics.getCount()).isEqualTo(9);
        assertThat(dishStatistics.getMax()).isEqualTo(800);
    }

    @Test
    @DisplayName("joining - 문자열 조인")
    void joining() {
        String joinNames = Dishes.getDefault()
            .stream()
            .map(Dish::getName)
            .collect(Collectors.joining(","));
    }

    @Test
    @DisplayName("maxBy")
    void max() {
        Optional<Dish> maxCalorieDish = Dishes.getDefault()
            .stream()
            .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

        Optional<Dish> maxCalorieDish2 = Dishes.getDefault()
            .stream()
            .max(Comparator.comparingInt(Dish::getCalories));
    }

    @Test
    @DisplayName("minBy")
    void min() {
        Optional<Dish> minCalorieDish = Dishes.getDefault()
            .stream()
            .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));

        Optional<Dish> minCalorieDish2 = Dishes.getDefault()
            .stream()
            .min(Comparator.comparingInt(Dish::getCalories));
    }

    @Test
    @DisplayName("reduce")
    void reduce() {
        int sum = Dishes.getDefault()
            .stream()
            .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        int sum2 = Dishes.getDefault()
            .stream()
            .map(Dish::getCalories)
            .reduce(0, Integer::sum);

        int sum3 = Dishes.getDefault()
            .stream()
            .mapToInt(Dish::getCalories)
            .sum();
    }

    @Test
    @DisplayName("collectingAndThen - 변환 함수가 형식을 반환한다.")
    void collectingAndThen() {
        int dishes = Dishes.getDefault()
            .stream()
            .collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
    }

    @Test
    @DisplayName("groupingBy")
    void groupingBy() {
        Map<Dish.Type, List<Dish>> collect = Dishes.getDefault()
            .stream()
            .collect(Collectors.groupingBy(Dish::getType));
    }

    @Test
    @DisplayName("partitioningBy")
    void partitioningBy() {
        Map<Boolean, List<Dish>> collect = Dishes.getDefault()
            .stream()
            .collect(Collectors.partitioningBy(Dish::getVegetarian));

        Map<Boolean, Map<Dish.Type, List<Dish>>> dishesMap2 = Dishes.getDefault()
            .stream()
            .collect(Collectors.partitioningBy(Dish::getVegetarian,
                                               Collectors.groupingBy(Dish::getType)));
    }

    @Test
    @DisplayName("커스텀 컬렉터 구현")
    void customCollect() {
        List<Dish> collect = Dishes.getDefault()
            .stream()
            .filter(Dish::getVegetarian)
            .collect(new CustomCollector<>());

        assertThat(collect.size()).isEqualTo(4);
    }
}
