package streamex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamIntroTest {

    @Test
    @DisplayName("스트림 소개 한줄 요약")
    public void streamIntro() {
        List<String> dishNames = Dishes.getDefault()
            .stream()
            // 중간 연산(내부 반복)
            .filter(d -> d.getCalories() > 300)
            .map(Dish::getName)
            .skip(1L)
            .limit(3)
            // 최종 연산
            .collect(Collectors.toList());

        assertThat(dishNames.get(0)).isEqualTo("beef");
    }
}
