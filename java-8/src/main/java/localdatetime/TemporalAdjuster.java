package localdatetime;

import java.time.temporal.Temporal;

@FunctionalInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
