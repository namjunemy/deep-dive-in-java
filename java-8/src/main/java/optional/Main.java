package optional;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // map() 메서드로 Optional 값을 추출하고 변환하기
        Insurance insurance = new Insurance("한화생명");
        Insurance insurance2 = new Insurance(null);
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<Insurance> optionalInsurance2 = Optional.ofNullable(insurance2);

        // Optional이 값을 포함하면 map의 인수로 제공된 함수가 값을 바꾼다.
        // Optional이 비어있으면 아무 일도 일어나지 않는다.
        Optional<String> name1 = optionalInsurance.map(Insurance::getName);
        System.out.println("보험사이름 : " + name1.orElse("이름없음"));

        Optional<String> name2 = optionalInsurance2.map(Insurance::getName);
        System.out.println("보험사이름 : " + name2.orElse("null 이라서 이름없음"));


        Car car = new Car(optionalInsurance);
        Person person = new Person(Optional.ofNullable(car));
        Optional<Person> optionalPerson = Optional.of(person);

        // 아래의 map() 체인은 컴파일 에러가 난다.
        // optionalPerson은 Optional<Person> 이므로 map() 을 호출할 수 있지만,
        // Person의 getCar()는 Optional<Car>를 반환한다. 따라서 연산결과는 Optional<Optional<Car>> 형식의 객체가 된다.
        Optional<String> name3 =
            optionalPerson.map(Person::getCar)
//                          .map(Car::getInsurance)
                          // 굳이 체인으로 걸려면 이런식으로 풀어야 한다.
                          // Optional<Optional<Car>> 에서 get()으로 꺼내서 -> Optional<Car>
                          // getInsurance()는 다시 Optional<Optional<Insurance>> 를 반환하므로 다시 get()
                          // 결국 Optional<Insurance>를 map()으로 처리하게 된다.
                          .map(doubleOptCar -> doubleOptCar.get().getInsurance().get())
                          .map(Insurance::getName);

        System.out.println("map() 보험사이름 : " + name3.orElse("이름없음"));


        // flatMap() 메서드로 Optional 객체를 연결할 수 있다.
        // fiatMap은 함수를 인수로 받아서 다름 스트림을 반환한다.
        // 인수로 받은 함수를 적용해서 생성된 각각의 스트림에서 콘텐츠만 남긴다.
        // 즉, 함수를 적용해서 생선된 모든 스트림이 하나의 스트림으로 병한되어 평준화 된다.
        // 이차원 Optional을 일차원 Optional로 평준화하는데 사용한다.

        // 빈 Optional에서 flatMap() 을 호출하면 아무 일도 일어나지 않고 그대로 반환된다.
        // optionalPerson이 empty라면 orElse()를 타서 이름없음 이 출력된다.
        // 마찬가지로 flatMap(Person::getCar)에서 리턴된 Optional<Car>가 empty 여도 같은 상황이다.
        // optionalPerson = Optional.empty();
        String name4 =
            optionalPerson.flatMap(Person::getCar)
                          .flatMap(Car::getInsurance)
                          .map(Insurance::getName)
                          .orElse("이름없음");

        System.out.println("flatMap() 보험사이름 : " + name4);
    }
}
