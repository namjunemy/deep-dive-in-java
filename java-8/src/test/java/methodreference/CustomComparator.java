package methodreference;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class CustomComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }

    @Override
    public Comparator<String> reversed() {
        return null;
    }

    @Override
    public Comparator<String> thenComparing(Comparator<? super String> other) {
        return null;
    }

    @Override
    public <U> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<String> thenComparing(Function<? super String, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<String> thenComparingInt(ToIntFunction<? super String> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<String> thenComparingLong(ToLongFunction<? super String> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<String> thenComparingDouble(ToDoubleFunction<? super String> keyExtractor) {
        return null;
    }
}
