package methodreference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdvancedFilteringApplesTest {

    private List<String> list;

    @BeforeEach
    void setUp() {
        list = Arrays.asList("c", "b", "a", "d");
    }

    @Test
    @Order(1)
    @DisplayName("1단계")
    void 동작_파라미터화() {
        list.sort(new CustomComparator());
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(3)).isEqualTo("d");
    }

    @Test
    @Order(2)
    @DisplayName("2단계")
    void 익명_클래스_사용() {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareToIgnoreCase(o1);
            }
        });

        assertThat(list.get(0)).isEqualTo("d");
        assertThat(list.get(3)).isEqualTo("a");
    }

    @Test
    @Order(3)
    @DisplayName("3단계")
    void 람다_표현식_사용() {
        list.sort((String o1, String o2) -> o1.compareToIgnoreCase(o2));
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(3)).isEqualTo("d");

        list.sort((o1, o2) -> o2.compareToIgnoreCase(o1));
        assertThat(list.get(0)).isEqualTo("d");
        assertThat(list.get(3)).isEqualTo("a");

        //가독성 향상(Comparator의 comparing 정적메서드 사용)
        Comparator<String> c = Comparator.comparing((String s) -> s);
        list.sort(c);
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(3)).isEqualTo("d");
    }

    @Test
    @Order(4)
    @DisplayName("4단계")
    void 메서드_레퍼런스_사용() {
        // Comparator의 comparing 메서드를 메서드 레퍼런스로 변경
        list.sort(Comparator.comparing(Function.identity()));
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(3)).isEqualTo("d");
    }
}
