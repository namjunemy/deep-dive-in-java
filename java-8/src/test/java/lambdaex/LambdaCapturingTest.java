package lambdaex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaCapturingTest {

    /**
     * 람다 표현식에서 파라미터로 넘겨진 변수가 아닌 외부에서 정의된 변수 가능 - 람다 캡처링
     * 인스턴스 변수와 정적 변수를 자유롭게 캡처 할 수 있다.
     * 하지만 변경가능성이 있으면 안된다. 명시적으로 final 이거나 실질적으로 final 로 선언된 것과 같이 변경 가능성이 없어야 한다.
     * 컴파일 에러 난다.
     */
    @Test
    @DisplayName("람다의 자유 변수 사용")
    void useFreeVariable() {
        int number = 1333;
        Runnable r = () -> System.out.println(number);
        r.run();
//        number = 1444;
    }
}
