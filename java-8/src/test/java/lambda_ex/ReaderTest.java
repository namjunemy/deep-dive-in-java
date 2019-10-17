package lambda_ex;


import org.junit.Test;

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