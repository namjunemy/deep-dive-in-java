package streamutilization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    @DisplayName("무한스트림 활용 iterate - 피보나치의 수")
    void iterate() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
            .limit(20)
            .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
            .limit(20)
            .mapToInt(t -> t[0])
            .forEach(System.out::println);
    }

    @Test
    @DisplayName("무한스트림 활용 generate - 로또")
    void generate() {
        Stream.generate(() -> Math.random() * 45 + 1)
            .mapToInt(Double::intValue)
            .limit(6)
            .forEach(System.out::println);
    }

    @Test
    @DisplayName("무한스트림 활용 generate - 피보나치")
    void intGenerate() {
        // fib 객체는 어떤 피보나치 요소가 들어있는지 추적하므로 가변(mutable) 상태 객체다.
        // 병렬 코드에서 공급자에 상태가 있으면 안전하지 않다.
        // 상태를 갖는 공급자는 실제로는 피해야하는 코드이다.
        // 중요!!! 스트림으로 병렬 처리하면서 올바른 결과를 얻으려면 immutable 한 상태를 유지해야 한다. iterate 방식 처럼
        IntSupplier fib = new IntSupplier() {
            int previous = 0;
            int current = 1;

            // 이 메서드에서 커스터마이즈 가능한 상태 필드를 정의 할 수 있다는 점이 다르다.
            // 위에서 람다로 처리한 무한 스트림 예제들은 부작용이 없다. immutable 하기 때문에
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib)
            .limit(10)
            .forEach(System.out::println);
    }
}
