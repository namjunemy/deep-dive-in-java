package localdatetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DurationAndPeriodService {
    public static void main(String[] args) {
        // LocalDateTime, LocalTime, Instant는 Temparal의 구현체이다.
        // 두개의 LocalDateTime, 두개의 LocalTime, 두개의 Instant로 Duration을 만들 수 있다.
        System.out.println("\n-- Duration");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.minusDays(1);
        Duration between = Duration.between(localDateTime, localDateTime1);
        System.out.println(between);

        Instant instant = Instant.now();
        Instant instant1 = instant.minusNanos(1_000_000_000);
        between = Duration.between(instant, instant1);
        System.out.println(between);

        System.out.println("\n-- Period");

        // LocalDateTime은 사람이, Instant는 기계가 사용하도록 만들어진 클래스로 두 인스턴스는 서로 혼합할 수 없다.
        // 또한, Duration 클래스는 초와 나노초로 시간 단위를 표현하므로 between 메서드에 LocalDate를 전달할 수 없다.
        // 년, 월, 일로 시간을 표현할 때는 Period 클래스를 사용한다.
        Period period = Period.between(localDateTime.toLocalDate(), localDateTime1.toLocalDate());
        System.out.println(period);

        System.out.println("\n-- Duration self");

        // Duration과 Period 클래스는 본인의 인스턴스를 만들 수 있도록 다양한 팩토리 메서드를 제공한다.
        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println(threeMinutes);
        threeMinutes = Duration.of(3, ChronoUnit.DAYS); // Duration은 Days를 나타내지 못한다. 최대 시간.
        System.out.println(threeMinutes);
        threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes);
        threeMinutes = Duration.of(3, ChronoUnit.SECONDS);
        System.out.println(threeMinutes);
        threeMinutes = Duration.of(3, ChronoUnit.NANOS);
        System.out.println(threeMinutes);

        System.out.println("\n-- Period self");

        Period tenDays = Period.ofDays(10);
        System.out.println(tenDays);
        Period threeWeeks = Period.ofWeeks(3);
        System.out.println(threeWeeks);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay);
    }
}
