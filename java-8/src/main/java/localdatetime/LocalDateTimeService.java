package localdatetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeService {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 03, 16);
        LocalTime time = LocalTime.of(20, 54, 30);
        LocalDateTime d1 = LocalDateTime.of(2014, Month.MARCH, 16, 20, 54, 30);
        LocalDateTime d2 = LocalDateTime.of(date, time);
        LocalDateTime d3 = date.atTime(20, 54, 30);
        LocalDateTime d4 = date.atTime(time);
        LocalDateTime d5 = time.atDate(date);

        System.out.println(d1.equals(d5));
    }
}
