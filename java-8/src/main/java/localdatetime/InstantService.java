package localdatetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

public class InstantService {

    public static void main(String[] args) {
        // Instant는 기계를 위한 날짜와 시간 표현 이다. 초와 나노초 정보를 포함한다.
        Instant instant = Instant.ofEpochMilli(3);
        System.out.println(instant);
        System.out.println(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), ZoneOffset.UTC));

        instant = Instant.ofEpochSecond(3, 0);
        System.out.println(instant);

        instant = Instant.ofEpochSecond(2, 1_000_000_000);
        System.out.println(instant);

        instant = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println(instant);

        instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        System.out.println(instant);

        // Instant는 기계 전용의 우틸리티 이므로, 사람이 읽을 수 있는 시간의 정보를 제공하지 않는다.
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
        int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
        System.out.println(day);
    }
}
