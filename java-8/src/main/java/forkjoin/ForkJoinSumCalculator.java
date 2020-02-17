package forkjoin;

import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length, "init");
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end, String forkAt) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        log.info("{} fork {} {} {}", forkAt, start, start + (end - start) / 2, end);
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            log.info("sum sequentially {} {} {}", start, start + length / 2, end);
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2, "left");
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end, "right");
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
