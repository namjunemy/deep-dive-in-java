package behavior_parameterization;

import java.util.ArrayList;
import java.util.List;

public class FilteringFruits {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
