package streamutilization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("스트림 활용 실전 연습")
public class StreamUtilExerciseTest {

    private static final String CAMBRIDGE = "Cambridge";
    private static final String MILAN = "Milan";

    private final List<Transaction> transactions = getDefaultList();

    private List<Transaction> getDefaultList() {
        Trader raoul = new Trader("Raoul", CAMBRIDGE);
        Trader mario = new Trader("Mario", MILAN);
        Trader alan = new Trader("Alan", CAMBRIDGE);
        Trader brian = new Trader("Brian", CAMBRIDGE);

        return Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
    }

    @Test
    @Order(1)
    @DisplayName("2011년에 일어난 모든 트랜잭션 오름차순")
    void ex01() {
        List<Transaction> results = transactions.stream()
            .filter(t -> 2011 == t.getYear())
            .sorted(Comparator.comparing(Transaction::getValue).reversed())
            .collect(Collectors.toList());

        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getValue()).isEqualTo(400);
    }

    @Test
    @Order(2)
    @DisplayName("거래자가 근무하는 모든 도시를 중복 없이 나열")
    void ex02() {
        List<String> cities = transactions.stream()
            .map(t -> t.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());

        assertThat(cities.size()).isEqualTo(2);
    }

    @Test
    @Order(3)
    @DisplayName("Cambridge 에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬")
    void ex03() {
        List<Trader> traders = transactions.stream()
            .map(Transaction::getTrader)
            .filter(t -> CAMBRIDGE.equals(t.getCity()))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());

        assertThat(traders.size()).isEqualTo(3);
        assertThat(traders.get(0).getName()).isEqualTo("Alan");
    }

    @Test
    @Order(4)
    @DisplayName("모든 거래자의 이름을 알파벳 순(사전순)으로 정렬해서 반환")
    void ex04() {
        List<String> names = transactions.stream()
            .map(Transaction::getTrader)
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .map(Trader::getName)
            .collect(Collectors.toList());

        assertThat(names.size()).isEqualTo(4);
        assertThat(names.get(0)).isEqualTo("Alan");
    }

    @Test
    @Order(5)
    @DisplayName("밀라노에 거래자가 있는가?")
    void ex05() {
        boolean isExists = transactions.stream()
            .anyMatch(t -> MILAN.equals(t.getTrader().getCity()));

        assertTrue(isExists);
    }

    @Test
    @Order(6)
    @DisplayName("케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오")
    void ex06() {
        List<Integer> values = transactions.stream()
            .filter(t -> CAMBRIDGE.equals(t.getTrader().getCity()))
            .map(Transaction::getValue)
            .collect(Collectors.toList());

        values.forEach(System.out::println);

        assertThat(values.size()).isEqualTo(4);
    }

    @Test
    @Order(7)
    @DisplayName("전체 트랜잭션 중 최댓값과 최솟값은?")
    void ex07() {
        Optional<Integer> min = transactions.stream()
            .map(Transaction::getValue)
            .min(Comparator.comparing(Function.identity()));

        Optional<Transaction> maxT = transactions.stream()
            .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2);

        assertThat(min.orElse(0)).isEqualTo(300);
        assertThat(maxT.get().getValue()).isEqualTo(1000);
    }
}
