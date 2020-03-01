package optional;

import java.util.Optional;
import java.util.Properties;

public class ConfigService {

    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
            }
        }
        return 0;
    }

    public int readDurationOptional(Properties props, String name) {
        // props를 Optional 객체로 만든다.
        // flatMap으로 Optional<Optional<Integer>>를 Optional<Integer>로 만들고
        // Optional의 filter로 Optional 객체를 걸러서, 해당 되지 않으면 기본 값 0을 리턴한다.
        return Optional.ofNullable(props.getProperty(name))
                       .flatMap(ConfigService::stringToInt)
                       .filter(i -> i > 0)
                       .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
}
