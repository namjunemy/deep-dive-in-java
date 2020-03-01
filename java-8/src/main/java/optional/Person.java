package optional;

import java.util.Optional;

public class Person {

    private int age;
    private Optional<Car> car;

    public Person(int age, Optional<Car> car) {
        this.age = age;
        this.car = car;
    }

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
