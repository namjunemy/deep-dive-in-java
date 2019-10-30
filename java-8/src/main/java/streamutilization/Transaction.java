package streamutilization;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public static List<Transaction> getDefaultList() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "trader=" + trader +
            ", year=" + year +
            ", value=" + value +
            '}';
    }
}
