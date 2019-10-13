package behavior_parameterization;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Banana {
    private String color;
    private int weight;

    @Builder
    public Banana(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }
}
