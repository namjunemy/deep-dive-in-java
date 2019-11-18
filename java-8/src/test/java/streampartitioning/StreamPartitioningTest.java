package streampartitioning;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("스트림 파티셔닝 테스트")
public class StreamPartitioningTest {

    @Test
    @Order(1)
    @DisplayName("소수와 비소수로 분할하기")
    public void partition01() {
        int candidate = 10;
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, candidate)
            .boxed()
            .collect(Collectors.partitioningBy(this::isPrime));

        int nonPrime = collect.get(false).size();
        int prime = collect.get(true).size();

        assertThat(nonPrime).isEqualTo(5); //[4, 6, 8, 9, 10]
        assertThat(prime).isEqualTo(4);  //[2, 3, 5, 7]
    }

    private boolean isPrime(int candidate) {
        // 소수의 대상을 주어진 수의 제곱근 이하의 수로 제한 할 수 있음.
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
            .noneMatch(i -> candidate % i == 0);
    }
}
