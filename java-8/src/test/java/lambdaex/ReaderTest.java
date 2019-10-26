package lambdaex;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReaderTest {

    /**
     * 추상 메서드가 오직 하나면 함수형 인터페이스이다.(디폴트 메서드 가능)
     */
    @Test
    public void 함수형_인터페이스와_람다_제대로_이해하기() {
        // 람다 사용
        Runnable run = () -> System.out.println("run");

        // 익명 클래스 사용
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("run2");
            }
        };

        process(run);
        process(run2);
        // 동일 메서드 시그니처에 맞는 직접 전달된 람다 표현식
        process(() -> System.out.println("run3"));
    }

    private static void process(Runnable r) {
        r.run();
    }

    /**
     * 실행 어라운드 패턴
     * 리소스 설정과 정리 과정은 재사용하고 processFile 메서드만 동작 파라미터화 해서 다른 동작을 수행할 수 있다.
     *
     * 동작에 해당하는 BufferedReader -> String, throws IOException 메서드 시그니처를 던질 수 있는
     * 함수형 인터페이스(BufferedReaderProcessor)를 만들어야 한다. 그러면, processFile 의 인수로 인터페이스(익명클래스, 람다)를 전달할 수 있다.
     * 결과적으로 함수형 인터페이스 BufferedReaderProcessor 에 정의된
     * process 메서드의 시그니처와 일치하는 람다(BufferedReader -> String)를 전달 할 수 있다.
     *
     * BufferedReaderProcessor 에 정의된 processor 메서드의 시그니처 (BufferedReader) -> String 과
     * 일치하는 람다를 전달해서 함수형 인터페이스의 인스턴스로 전달된 코드와 같은 방식으로 처리한다.
     *
     * @throws IOException
     */
    @Test
    public void 실행_어라운드_패턴_테스트() throws IOException {
        String resultOne = Reader.processFile((BufferedReader br) -> br.readLine());
        String resultTwo = Reader.processFile((BufferedReader br) -> br.readLine() + br.readLine());

        assertThat(resultOne.length()).isEqualTo(3);
        assertThat(resultTwo.length()).isEqualTo(6);
    }
}