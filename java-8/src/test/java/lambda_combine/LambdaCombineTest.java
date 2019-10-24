package lambda_combine;

import common.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaCombineTest {
    private List<Apple> list;

    @BeforeEach
    void setUp() {
        list = Arrays.asList(new Apple("A", 3, 1),
                             new Apple("B", 4, 1),
                             new Apple("C", 5, 4),
                             new Apple("D", 5, 2));
    }


    @Test
    @DisplayName("Comporator 조합")
    void combineComparator() {
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        list.sort(c);
        assertThat(list.get(0).getName()).isEqualTo("A");

        list.sort(c.reversed());
        assertThat(list.get(3).getName()).isEqualTo("A");
    }

    @Test
    @DisplayName("Comporator 연결 - 등급(사과 weight로 정렬 후, 겹치면 rank 추가 정렬")
    void linkComparator() {
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        list.sort(c
                      .reversed() // 무게 내림차순으로 정렬
                      .thenComparing(Apple::getRank));

        assertThat(list.get(0).getName()).isEqualTo("D");
        assertThat(list.get(1).getName()).isEqualTo("C");
    }

    @Test
    @DisplayName("Predicate 조합")
    void combinePredicate() {
        Predicate<Apple> topRankApple = (Apple a) -> 1 == a.getRank();
        // negate -> predicate 의 부정
        Predicate<Apple> notTopRankApple = topRankApple.negate();
        // 랭크 1 제외
        List<Apple> excludeTopRankApples = list.stream()
            .filter(notTopRankApple)
            .collect(Collectors.toList());

        assertThat(excludeTopRankApples.size()).isEqualTo(2);

        // 랭크 1, 3키로 이상
        List<Apple> topRankAppleAndOver3kgApples = list.stream()
            .filter(topRankApple.and((Apple a) -> a.getWeight() > 3))
            .collect(Collectors.toList());

        assertThat(topRankAppleAndOver3kgApples.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Function 조합")
    void combineFunction() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);

        assertThat(h.apply(2)).isEqualTo(6);

        h = f.compose(g);
        assertThat(h.apply(4)).isEqualTo(9);
    }
}
