package behaviorparameterization;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Apple {
    private String color;
    private int weight;

    @Builder
    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }
}
