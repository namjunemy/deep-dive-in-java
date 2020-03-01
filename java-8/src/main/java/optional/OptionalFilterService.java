package optional;

import java.util.Optional;

public class OptionalFilterService {

    public static void main(String[] args) {

        Insurance insurance = new Insurance("메리츠화재");
        if (insurance != null && "메리츠화재".equals(insurance.getName())) {
            System.out.println("메리츠화재 연결 가능");
        }

        // Optional의 filter는 Predicate를 인수로 가진다.
        // Optional 객체가 값을 가지면서, Predicate 와 일치하면 그 값을 반환하고, 아니라면 빈 Optional 객체를 반환한다.
        // Optional은 최대 한 개의 요소를 포함할 수 있는 스트림과 같다. filter의 원리도 같다.
        Optional<Insurance> optionalInsurance = Optional.of(insurance);
        optionalInsurance.filter(optInsurance -> "메리츠화재".equals(optInsurance.getName()))
                         .ifPresent(x -> System.out.println("메리츠화재 연결 가능"));

        // filter()를 활용해서 getCarInsuranceName() 의 조건을 15세 이상의 나이로만 설정해보자.
        String name = getCarInsuranceName(Optional.of(new Person(18, Optional.of(new Car(Optional.of(new Insurance("한화생명")))))), 15);
        System.out.println(name);
        String name2 = getCarInsuranceName(Optional.of(new Person(13, Optional.of(new Car(Optional.of(new Insurance("한화생명")))))), 15);
        System.out.println(name2);
    }

    public static String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
            .flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("15세 이상은 보험사 이름 출력 불가");
    }
}
