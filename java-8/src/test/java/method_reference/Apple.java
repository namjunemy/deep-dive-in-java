package method_reference;

import lombok.Getter;

@Getter
class Apple {
    private String name;
    private int weight;
    private int rank;

    Apple() {

    }

    Apple(String name) {
        this.name = name;
    }

    Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    Apple(String name, int weight, int rank) {
        this.name = name;
        this.weight = weight;
        this.rank = rank;
    }
}
