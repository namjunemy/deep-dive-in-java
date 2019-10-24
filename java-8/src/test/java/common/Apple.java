package common;

import lombok.Getter;

@Getter
public class Apple {
    private String name;
    private int weight;
    private int rank;

    public Apple() {

    }

    public Apple(String name) {
        this.name = name;
    }

    public Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Apple(String name, int weight, int rank) {
        this.name = name;
        this.weight = weight;
        this.rank = rank;
    }
}
