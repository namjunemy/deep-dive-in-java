package method_reference;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodReferenceTest {

    /**
     * 메서드 구현을 재활용해서 메서드 레퍼런스 만들기
     */
    @Test
    @DisplayName("메서드 레퍼런스 이해")
    void methodReference() {
        // 첫번째 인수의 메서드 호출하는 방식
        List<String> targetList = Arrays.asList("a", "b", "c", "d");
        targetList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        targetList.sort(String::compareToIgnoreCase);

        assertThat(stringToInteger((String s) -> Integer.parseInt(s), "33")).isEqualTo(33);
        // 1. 정적 메서드를 호출하는 람다 표현식
        assertThat(stringToInteger(Integer::parseInt, "43")).isEqualTo(43);

        targetList = Arrays.asList("a", "b", "c");
        String target = "a";
        assertThat(contains((list, s) -> list.contains(s), targetList, target)).isTrue();
        // 2. 첫번째 인수의 메서드 호출하는 방식
        assertThat(contains(List::contains, targetList, target)).isTrue();
    }

    private Integer stringToInteger(Function<String, Integer> stringIntegerFunction, String s) {
        return stringIntegerFunction.apply(s);
    }

    private boolean contains(BiPredicate<List<String>, String> biPredicate,
                             List<String> list,
                             String target) {
        return biPredicate.test(list, target);
    }

    @Test
    @DisplayName("생성자 레퍼런스")
    void constructorReference() {
        // 기본 생성자
        Supplier<Apple> s1 = Apple::new;
        assertThat(s1.get().getName()).isBlank();

        // 두 인수를 갖는 생성자
        BiFunction<String, Integer, Apple> bF = Apple::new;
        Apple apple = bF.apply("apple1", 3);
        assertThat(apple.getName()).isEqualTo("apple1");

        // 세 인수를 갖는 생성자(커스텀 함수형 인터페이스 정의)
        TriFunction<String, Integer, Integer, Apple> tF = Apple::new;
        apple = tF.apply("apple2", 3, 1);
        assertThat(apple.getName()).isEqualTo("apple2");
    }
}
