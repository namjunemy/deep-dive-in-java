package forkjoin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("포크조인 프레임워크")
class ForkJoinSumCalculatorTest {

    @Test
    @DisplayName("포크조인 프레임워크를 이용해서 병렬 합계 계산")
    void sum() {
        long n = 100_000;
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long result = new ForkJoinPool().invoke(task);
        assertThat(result).isEqualTo(5000050000L);
    }
}