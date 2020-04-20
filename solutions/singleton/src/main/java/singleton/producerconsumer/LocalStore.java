package singleton.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class LocalStore implements Store {

    private final List<Product> products;
    public static final int CAPACITY = 3000;
    private static LocalStore instance;

    private LocalStore() {
        this.products = new ArrayList<>();
    }

    public static LocalStore getInstance() {
        if (instance == null) {
            instance = new LocalStore();
        }
        return instance;
    }

    public void reset() {
        products.clear();
    }

    @Override
    public void add(Product product) {

        if (products.size() >= CAPACITY) {
            return;
        }

        this.products.add(product);
    }

    @Override
    public Product remove() {

        if (products.isEmpty()) {
            throw new IllegalStateException("Store is empty, no product is available!");
        }
        return this.products.remove(0);
    }

    @Override
    public int getProductCount() {
        return products.size();
    }
}
