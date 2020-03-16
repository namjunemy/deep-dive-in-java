package localdatetime;

import java.time.*;
import java.util.TimeZone;

/*
다양한 시간대와 캘린더 활용 방법
 */
public class ZondIdService {
    public static void main(String[] args) {
        /*
            새로운 날짜와 시간 API의 큰 편리함 중 하나는 시간대를 간단하게 처리할 수 있다는 점이다.
            기존의 java.util.TimeZone을 대체할 수 있는 java.time.ZoinId 클래스가 등장헀다.
            새로운 클래스를 이용하면 서머타임(Daylight Saving Time)같은 복잡한 사항이 자동으로 처리된다.

            날짜와 시간 API에서 제공하는 다른 클래스와 마찬가지로 ZoneId는 불변 클래스다.

            표준 시간이 같은 지역을 묶어서 시간대로 규정한다.
            ZoneRules 클래스에는 약 40개 정도의 시간대가 있다.
            ZoneId의 getRules()를 이용해서 해당 시간대의 규정을 획득할 수 있다.
            아래 처럼 지역 ID 로 특정 ZoneId를 구분한다.
         */

        ZoneId.getAvailableZoneIds().stream()
              .sorted()
              .forEach(System.out::println);

        System.out.println();

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        System.out.println("서울의 Zone 규칙 : " + seoulZone.getRules());

        System.out.println("기본 타임 존 : " + TimeZone.getDefault().getID());

        /*
            ZoneId 객체를 얻은 다음에는 LocalDate, LocalDateTime, Instant를 이용해서 ZonedDateTime 인스턴스로 변환할 수 있다.
            ZonedDateTime은 지정한 시간에 상대적인 시점을 표현한다.

            ZonedDateTime의 개념 영역으로 이해하기.
            ________________________________________
            |  LocalDate  |  LocalTime  |  ZoneId  |
            |       LocalDateTime       |
            |             ZonedDateTime            |

         */

        System.out.println("\nLocalDate, LocalDateTime, Instant To ZoneDateTime 변환");
        LocalDate date = LocalDate.now();
        ZonedDateTime zonedDateTime1 = date.atStartOfDay(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime1 : " + zonedDateTime1);

        LocalDateTime now = LocalDateTime.now().minusDays(2);
        ZonedDateTime zonedDateTime2 = now.atZone(ZoneId.of("Europe/Rome"));
        System.out.println("zonedDateTime2 : " + zonedDateTime2);

        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime3 = instant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime3 : " + zonedDateTime3);

        System.out.println("\nZoneId를 활용해서 LocalDateTime To Instant and Instant to LocalDateTime");
        Instant nowInstant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        System.out.println(nowInstant);
        Instant instantNow = Instant.now();
        LocalDateTime nowFromInstant = LocalDateTime.ofInstant(instantNow, ZoneId.of("Asia/Seoul"));
        System.out.println(nowFromInstant);
    }
}
