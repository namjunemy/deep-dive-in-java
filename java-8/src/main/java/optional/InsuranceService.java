package optional;

import java.util.Optional;

public class InsuranceService {

    public Insurance findChipestInsurance(Person person, Car car) {
        // 다양한 보험회사가 제공하는 서비스 조죄
        // 모든 결과 데이터 비교해서
        Insurance chepestCompany = new Insurance("제일 싼 회사");
        return chepestCompany;
    }

    // 두 Optional을 인수로 받아서 Optional<Insurance>를 반환하는 null-safety 버전의 메서드
    public Optional<Insurance> nullSafeFindChipestInsurance(Optional<Person> person, Optional<Car> car) {
        // 보통 Optional 파라미터가 존재 하는지 검증하는 로직을 먼저 수행한다.
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findChipestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    //nullSafeFindChipestInsurance()를 Optional을 Unwarp 하지 않고 두 Optional을 합치는 메소드를 한줄로 작성할 수 있다.
    public Optional<Insurance> nullSafeFindChipestInsurance2(Optional<Person> person, Optional<Car> car) {

        // flatMap() 과 map()을 활용해서 굉장히 심플하게 재구현했다.
        // 첫번째 Optional<Person>이 flatMap을 호출했으므로, 첫번째 Optional이 비어있다면 람다를 실행하지 않고, 그대로 빈 Optional이 반환된다.
        // 반면에 person값이 있으면, flatMap()에 필요한 Optional<Insurance>를 반환하는 findChipestInsurance로 p를 전달한다.
        // car.map(c -> findChipestInsurance(p, c)) 이 반환하는 타입은 Optional<Optional<Insurance> 이므로 flatMap으로 꺼내기
        // 이 함수의 바디에서 두번째 Optional에 map()을 호출하므로 Optional이 car를 포함하지 않으면 Function은 빈 Optional을 반환한다.
        // 마지막으로, person과 car가 모두 존재하면 map() 으로 전달한 람다표현식이 findChipestInsurance()를 안전하게 호출할 수 있다.
        return person.flatMap(p -> car.map(c -> findChipestInsurance(p, c)));
    }
}
