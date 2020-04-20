package singleton.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class StoreTestImpl implements Store {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public int getProductCount() {
        return products.size();
    }

    @Override
    public Product remove() {
        return products.remove(0);
    }
}
