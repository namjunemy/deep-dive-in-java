package designpatternrefactoring.templatemethod;

import java.util.function.Consumer;

public class LambdaOnlineBanking {

    public void processCustomer(Long id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id).orElse(null);
        makeCustomerHappy.accept(c);
    }
}
