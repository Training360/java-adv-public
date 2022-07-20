package webshop;

import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {

    @Override
    public int compare(Product one, Product other) {
        return one.getName().compareTo(other.getName());
    }
}
