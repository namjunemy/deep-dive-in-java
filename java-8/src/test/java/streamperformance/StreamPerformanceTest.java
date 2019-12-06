package streamperformance;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("스트림 성능 테스트")
@Slf4j
class StreamPerformanceTest {

    @DisplayName("sequentialSum 성능")
    @Test
    @Order(1)
    void sequentialSum() {
        log.info("Sequential sum done in: {} msecs", measureSumPerformance(ParallelStreams::sequentialSum, 30_000_000));
    }

    @DisplayName("iterativeSum 성능")
    @Test
    @Order(2)
    void iterativeSum() {
        log.info("Iterative sum done in: {} msecs", measureSumPerformance(ParallelStreams::iterativeSum, 30_000_000));
    }

    @DisplayName("parallelSum 성능")
    @Test
    @Order(3)
    void parallelSum() {
        log.info("Parallel sum done in: {} msecs", measureSumPerformance(ParallelStreams::parallelSum, 30_000_000));
    }

    @DisplayName("특화된 메서드 rangeClosed - boxed 오버헤드, 청크")
    @Test
    @Order(4)
    void rangeClosedSum() {
        log.info("RangeClosed sum done in: {} msecs", measureSumPerformance(ParallelStreams::rangedSum, 30_000_000));
    }

    @DisplayName("특화된 메서드 rangeClosed Parallel - boxed 오버헤드, 청크")
    @Test
    @Order(5)
    void rangeClosedParallelSum() {
        log.info("RangeClosed Parallel sum done in: {} msecs", measureSumPerformance(ParallelStreams::parallelRangedSum, 30_000_000));
    }

    private long measureSumPerformance(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            log.info("result: {}", sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }

        return fastest;
    }
}
