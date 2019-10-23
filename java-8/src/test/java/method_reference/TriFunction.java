package method_reference;

public interface TriFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}
