package localdatetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.*;

/*
날짜 조정 활용
 */
public class LocalDateAdjustService {
    public static void main(String[] args) {

        // 1. 절대적인 방식으로 LocalDate의 속성을 바꾸는 방법
        LocalDate date1 = LocalDate.of(2020, 3, 16);
        LocalDate date2 = date1.withYear(2019);
        System.out.println(date2);
        LocalDate date3 = date2.withDayOfMonth(1);
        System.out.println(date3);

        /*
            첫번째 인수로 TemporalField를 갖는 메서드를 사용하면 좀 더 범용 적으로 메서드를 활용할 수 있다.

            위의 with()는 LocalDate.get(ChronoField.DAYS_OF_MONTH) 메서드와 쌍을 이룬다.
            with()와 get()은 날짜와 시간 API 의 모든 클래스가 구현하는 Temporal 인터페이스에 정의되어 있다.
            Temporal 인터페이스는 LocalDate, LocalDateTime, Instant 처럼 특정 시간을 정의 한다.
            정확히 표현하자면, get()과 with()로 Temporal 객체의 필드값을 읽거나 고칠 수 있다.
         */
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 8);
        System.out.println(date4);

        /*
            주의할 점.
            Instant 객체에 ChronoField.MONTH_OF_YEAR를 사용하거나,
            LocalDate 에 ChronoField.NANO_OF_SECOND 사용하는 등,
            Temporal 객체가 지정된 필드를 지원하지 않으면 UnsupportedTemporalTypeException 발생.
         */

        // 2. 상대적인 방식으로 LocalDate 속성 바꾸기
        System.out.println("\n--");
        date2 = date1.plusWeeks(1);
        System.out.println(date2);
        date3 = date2.minusYears(2);
        System.out.println(date3);
        date4 = date3.plus(6, ChronoUnit.MONTHS);
        System.out.println(date4);

        /*
            특정 시점을 표현하는 날짜 시간 클래스의 공통 메서드.

            # static method
            from() - 주어진 Temporal 객체를 이용해서 클래스의 인스턴스를 생성
            now() - 시스템 시계로 Temporal 객체를 생성
            of() - 주어진 구성 요소에서 Temporal 객체의 인스턴스를 생성
            parse() - 문자열을 파싱해서 Temporal 객체를 생성

            # non-static method
            atOffset() - 시간대 오프셋과 Temporal 객체를 합침
            atZone() - 시간대와 Temporal 객체를 합침
            format() - 지정된 포매터를 이용해서 Temporal 객체를 문자열로 변환함(Instant는 미지원)
            get() - Temporal 객체의 상태를 읽음
            minus() - 특정 시간을 뺀 Temporal 객체의 복사본을 생성
            plus() - 특정 시간을 더한 Temporal 객체의 복사본을 생성
            with() - 일부 상태를 바꾼 Temporal 객체의 복사본을 생성
         */

        // 퀴즈. immutable 주의.
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2002);
        LocalDateTime dateTime = date.atStartOfDay();
        System.out.println(date);
        System.out.println("답 " + dateTime);


        System.out.println("\n--TemporalAdjusters");

        // 3. TemporalAdjusters 사용하기
        date = LocalDate.from(LocalDateTime.now());

        System.out.println("이번 달 마지막 날 : " + date.with(lastDayOfMonth()));
        System.out.println("올해의 마지막 날 : " + date.with(lastDayOfYear()));
        System.out.println("내년의 첫번째 날 : " + date.with(firstDayOfNextYear()));
        System.out.println("이번 달 둘째 주 화요일 : " + date.with(dayOfWeekInMonth(2, DayOfWeek.TUESDAY)));
        System.out.println("이번 달 마지막 화요일 : " + date.with(lastInMonth(DayOfWeek.TUESDAY)));
        System.out.println("돌아오는 월요일 : " + date.with(next(DayOfWeek.MONDAY)));
        System.out.println("돌아오는 일요일(오늘포함) : " + date.with(nextOrSame(DayOfWeek.SUNDAY)));

        // 4. Custom TemporalAdjuster 구현하기
        LocalDate nextWorkingDay = date.with(new NextWorkingDay());
        System.out.println(nextWorkingDay);
    }
}
