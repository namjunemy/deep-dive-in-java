package streamcollect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import streamex.Dish;
import streamex.Dishes;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스트림으로 데이터 수집")
public class StreamCollectTest {

    @Test
    @Order(1)
    @DisplayName("서브스트림을 활용 - n 수준 그룹화 연산")
    void grouping() {
        Map<Dish.Type, Map<String, List<Dish>>> map = Dishes.getDefault().stream()
            .collect(
                Collectors.groupingBy(Dish::getType, // 분류 함수
                                      Collectors.groupingBy(dish -> {
                                          if (dish.getCalories() <= 400) {
                                              return "DIET";
                                          } else if (dish.getCalories() <= 700) {
                                              return "NORMAL";
                                          } else {
                                              return "FAT";
                                          }
                                      }))
            );

        long typeSize = Dishes.getDefault().stream()
            .map(Dish::getType)
            .distinct()
            .count();

        assertThat(map.size()).isEqualTo(typeSize);
        assertThat(map.get(Dish.Type.MEAT).size()).isEqualTo(3);
        assertThat(map.get(Dish.Type.MEAT).get("FAT").size()).isEqualTo(1);
    }

    @Test
    @Order(2)
    @DisplayName("서브 그룹으로 데이터 수집 - 카운트")
    void grouping2() {
        Map<Dish.Type, Long> map = Dishes.getDefault().stream()
            .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        long typeSize = Dishes.getDefault().stream()
            .map(Dish::getType)
            .distinct()
            .count();

        assertThat(map.size()).isEqualTo(typeSize);
        assertThat(map.get(Dish.Type.MEAT)).isEqualTo(3);
        assertThat(map.get(Dish.Type.FISH)).isEqualTo(2);
        assertThat(map.get(Dish.Type.OTHER)).isEqualTo(4);
    }

    @Test
    @Order(2)
    @DisplayName("서브 그룹으로 데이터 수집 - max 칼로리")
    void grouping3() {
        Map<Dish.Type, Optional<Dish>> map = Dishes.getDefault().stream()
            .collect(Collectors.groupingBy(Dish::getType,
                                           maxBy(Comparator.comparingInt(Dish::getCalories))));

        assertThat(map.get(Dish.Type.MEAT).get().getName()).isEqualTo("pork");
    }

    @Test
    @Order(3)
    @DisplayName("컬렉터 결과를 다른 형식에 적용하기 - Optional 반환하지 않게 하기(안전한 코드 만들기)")
    void collectingAndThenTest() {
        Map<Dish.Type, Dish> map = Dishes.getDefault().stream()
            .collect(Collectors.groupingBy(Dish::getType, // 분류 함수
                                           collectingAndThen( // 컬렉터
                                                              maxBy(Comparator.comparingInt(Dish::getCalories)), // 컬렉터
                                                              Optional::get))); //변환 함수

        assertThat(map.get(Dish.Type.MEAT).getName()).isEqualTo("pork");
    }

    @Test
    @Order(5)
    @DisplayName("groupingBy와 다른 컬렉터 사용하기")
    void grouping4() {
        Map<Dish.Type, Integer> map = Dishes.getDefault().stream()
            .collect(Collectors.groupingBy(Dish::getType, // 분류 함수
                                           summingInt(Dish::getCalories)));

        assertThat(map.get(Dish.Type.MEAT)).isEqualTo(1900);


        Map<Dish.Type, Set<String>> map2 = Dishes.getDefault().stream()
            .collect(Collectors.groupingBy(Dish::getType, // 분류 함수
                                           mapping(dish -> {
                                               if (dish.getCalories() <= 400) {
                                                   return "DIET";
                                               } else if (dish.getCalories() <= 700) {
                                                   return "NORMAL";
                                               } else {
                                                   return "FAT";
                                               }
                                           }, toSet()))); // Set 으로 고정
//                                           }, toCollection(HashSet::new)))); // 컬렉션 선택

        assertThat(map2.get(Dish.Type.MEAT)).isEqualTo(Stream.of("DIET", "NORMAL", "FAT").collect(toSet()));
    }

}
