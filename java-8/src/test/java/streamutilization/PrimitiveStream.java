package streamutilization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

@DisplayName("기본 타입 특화 스트림")
public class PrimitiveStream {

    @Test
    @DisplayName("숫자 스트림 활용 - 피타고라스")
    void intStreamTest() {
        // 제곱근 계산 - Math.sqrt
        // 5 * 5 + b * b = c * c

        // 초기 - 연산을 2번씩 함
        IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt((a * a) + (b * b)) % 1 == 0)
                .mapToObj(b -> Arrays.asList(a, b, (a * a) + (b * b))))
            .forEach(System.out::println);

        // 개선 - 한번 연산으로 쭉 만들어놓고 필터링
        IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(1, 100)
                .mapToObj(b -> new double[]{a, b, Math.sqrt((a * a) + (b * b))})
                .filter(o -> o[2] % 1 == 0))
            .forEach(o -> System.out.println(o[0] + "," + o[1] + "," + o[2]));
    }
}
