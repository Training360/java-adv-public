package webshop;

import java.util.*;

public class Store {

    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return new HashSet<>(products);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be empty!");
        }
        if (products.contains(product)) {
            throw new IllegalArgumentException("Product with barcode: " + product.getBarcode() + " is already registered.");
        }
        products.add(product);
    }

    public Product getProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.getBarcode().equals(barcode)) {
                return product;
            }
        }
        throw new IllegalArgumentException("No product with barcode: " + barcode);
    }

    public Map<ProductCategory, List<Product>> getProductsByCategory() {
        Map<ProductCategory, List<Product>> productsByCategories = new HashMap<>();
        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if (productsByCategories.containsKey(category)) {
                productsByCategories.get(category).add(product);
            } else {
                productsByCategories.put(category, new ArrayList<>(Arrays.asList(product)));
            }
        }
        return productsByCategories;
    }

    public Product getCheapestProductByCategory(ProductCategory category) {
        List<Product> productsByCategory = getProductsByCategory().get(category);
        Product cheapest = productsByCategory.get(0);
        for (Product product : productsByCategory) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public List<Product> listProductsSortedByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new ProductPriceComparator());
        return sorted;
    }

    public List<Product> listProductsSortedByName() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(new ProductNameComparator());
        return sorted;
    }
}
