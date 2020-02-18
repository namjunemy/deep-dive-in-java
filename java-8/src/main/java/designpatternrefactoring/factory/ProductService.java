package designpatternrefactoring.factory;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class ProductService {

    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static void main(String[] args) {
        Product p = ProductFactory.createProduct("stock");

        // replace with lambda
        // 생성자에 여러 파라미터가 들어가면 글쎄?, 단수 supplier로는 해결할 수 없다.
        Product p2 = createProduct("stock");
    }

    private static Product createProduct(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) {
            log.info("create product : {}", name);
            return p.get();
        }
        throw new IllegalArgumentException("No such product : " + name);
    }
}
