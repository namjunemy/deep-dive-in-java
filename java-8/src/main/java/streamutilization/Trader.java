package streamutilization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Trader {
    private final String name;
    private final String city;

    @Override
    public String toString() {
        return "Trader{" +
            "name='" + name + '\'' +
            ", city='" + city + '\'' +
            '}';
    }
}
