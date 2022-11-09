package sorting.webshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WebShop {

    private List<Product> products = new ArrayList<>();

    public void addProducts(Product... product) {
        products.addAll(Arrays.asList(product));
    }

    public List<Product> getProductsOrderByName() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new Comparator<Product>() {
            @Override
            public int compare(Product one, Product other) {
                return one.getName().compareTo(other.getName());
            }
        });
        return sorted;
    }

    public List<Product> getProductsOrderByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new Comparator<Product>() {
            @Override
            public int compare(Product one, Product other) {
                return (int) (one.getPrice() - other.getPrice());
            }
        });
        return sorted;
    }

    public List<Product> getProductsOrderByDate() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new Comparator<Product>() {
            @Override
            public int compare(Product one, Product other) {
                return one.getDate().compareTo(other.getDate());
            }
        });
        return sorted;
    }
}
