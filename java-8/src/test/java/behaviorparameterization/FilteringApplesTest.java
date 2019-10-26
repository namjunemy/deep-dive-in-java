package behaviorparameterization;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FilteringApplesTest {

    private static final List<Apple> apples = createTestData();

    private static List<Apple> createTestData() {
        return Arrays.asList(new Apple("red", 160),
                             new Apple("green", 160),
                             new Apple("red", 150));
    }

    @Test
    public void Predicate_필터링_테스트() {
        //given

        //when
        List<Apple> redApples = FilteringApples.filterApples(apples, new AppleRedColorPredicate());
        List<Apple> heavyApples = FilteringApples.filterApples(apples, new AppleHeavyWeightPredicate());

        //then
        assertThat(redApples.size()).isEqualTo(2);
        assertThat(heavyApples.size()).isEqualTo(2);
    }

    @Test
    public void 익명클래스_필더링_테스트() {
        //given

        //when
        List<Apple> redApples =
            FilteringApples.filterApples(apples, new ApplePredicate() {
                @Override
                public boolean test(Apple apple) {
                    return "red".equals(apple.getColor());
                }
            });

        //then
        assertThat(redApples.size()).isEqualTo(2);
    }

    @Test
    public void 람다_필더링_테스트() {
        //given

        //when
        List<Apple> redApples =
            FilteringApples.filterApples(apples, (Apple apple) -> "red".equals(apple.getColor()));

        //then
        assertThat(redApples.size()).isEqualTo(2);
    }

    @Test
    public void 스트림으로_바로_필터링() {
        //given
        List<Apple> apples = createTestData();

        //when
        List<Apple> redApples =
            apples.stream()
                .filter(apple -> "red".equals(apple.getColor()))
                .collect(Collectors.toList());

        //then
        assertThat(redApples.size()).isEqualTo(2);
    }
}