package designpatternrefactoring.starategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    @DisplayName("전략 패턴")
    void validate() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean isNumeric = numericValidator.validate("aaaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean isAllLowerCase = lowerCaseValidator.validate("bbbbb");

        assertFalse(isNumeric);
        assertTrue(isAllLowerCase);
    }

    @Test
    @DisplayName("전략 패턴 람다로 리팩토링")
    void validateByLambda() {
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        boolean isNumeric = numericValidator.validate("aaaaa");
        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        boolean isAllLowerCase = lowerCaseValidator.validate("bbbbb");

        assertFalse(isNumeric);
        assertTrue(isAllLowerCase);
    }
}