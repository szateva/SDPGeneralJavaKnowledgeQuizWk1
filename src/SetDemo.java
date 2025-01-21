import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<Product> set = Set.of(
                new Product(0, "P1"),
                new Product(0, "P1"));
        System.out.print(set.size());
    }
}