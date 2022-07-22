package webshop;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product one, Product other) {
        return Integer.valueOf(one.getPrice()).compareTo(other.getPrice());
    }
}
