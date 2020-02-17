package designpatternrefactoring.templatemethod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@NoArgsConstructor
public class Database {

    static AtomicReference<List<Customer>> customers = new AtomicReference<>(new ArrayList<>());

    public static Optional<Customer> getCustomerWithId(Long id) {
        return customers.get()
            .stream()
            .filter(customer -> id.equals(customer.getId()))
            .findAny();
    }

    public static void addCustomers(Customer customer) {
        customers.get().add(customer);
    }
}
