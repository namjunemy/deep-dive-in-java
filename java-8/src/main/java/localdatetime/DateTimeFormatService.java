package localdatetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/*
날짜와 시간 객체 출력과 파싱
 */
public class DateTimeFormatService {
    public static void main(String[] args) {
        System.out.println("\nLocalDate to String");
        LocalDate localDate = LocalDate.now();
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE); // 20200316;
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2020-03-16;
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("\nString to LocalDate");
        LocalDate date1 = LocalDate.parse(s1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date1);
        LocalDate date2 = LocalDate.parse(s2, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date2);

        System.out.println("\nLocalDate.format() - DateTimeFormatter");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date1.format(formatter);
        System.out.println(formattedDate);

        System.out.println("\nLocalDate.parse() - DateTimeFormatter");
        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        System.out.println(parsedDate);

        System.out.println("\n이탈리아 전용 Formatter");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        String formattedItalian = date1.format(italianFormatter);
        System.out.println("formattedItalian : " + formattedItalian);
        LocalDate parsedFromItalian = LocalDate.parse(formattedItalian, italianFormatter);
        System.out.println("parsedFromItalian : " + parsedFromItalian);

        System.out.println("\nDateTimeFormatterBuilder()로 커스텀 DateTimeFormatter 만들기");
        italianFormatter = new DateTimeFormatterBuilder()
            .appendText(ChronoField.DAY_OF_MONTH)
            .appendLiteral(". ")
            .appendText(ChronoField.MONTH_OF_YEAR)
            .appendLiteral(" ")
            .appendText(ChronoField.YEAR)
            .parseCaseInsensitive()
            .toFormatter(Locale.ITALIAN);
        System.out.println("이탈리아 전용 포매터로 파싱 : " + LocalDate.parse(formattedItalian, italianFormatter));
        System.out.println("이탈리아 전용 포매터로 포맷 : " + LocalDate.now().format(italianFormatter));
    }
}
