package behavior_parameterization;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FilteringFruitsTest {

    @Test
    public void 필터링_대상_추상화하기() {
        //given
        List<Banana> bananas = Arrays.asList(new Banana("yellow", 160),
                                             new Banana("green", 160),
                                             new Banana("yellow", 150));

        //when
        // 익명 클래스 사용
        List<Banana> heavyBananas =
            FilteringFruits.filter(bananas, new Predicate<Banana>() {
                @Override
                public boolean test(Banana banana) {
                    return banana.getWeight() > 150;
                }
            });
        // 람다 사용
        List<Banana> yellowBananas =
            FilteringFruits.filter(bananas, (Banana banana) -> "yellow".equals(banana.getColor()));
        // 스트림 필터링
        List<Banana> greenBananas =
            bananas.stream()
                .filter(banana -> "green".equals(banana.getColor()))
                .collect(Collectors.toList());
        //then
        assertThat(yellowBananas.size()).isEqualTo(2);
        assertThat(heavyBananas.size()).isEqualTo(2);
        assertThat(greenBananas.size()).isEqualTo(1);
    }
}
