package designpatternrefactoring.templatemethod;

import java.util.Objects;

public abstract class OnlineBanking {

    public void processCustomer(Long id) {
        Customer c = Database.getCustomerWithId(id).orElse(null);

        if (!Objects.isNull(c)) {
            makeCustomerHappy(c);
        }
    }

    abstract void makeCustomerHappy(Customer c);
}
