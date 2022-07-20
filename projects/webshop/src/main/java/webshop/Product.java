package webshop;

import java.util.Objects;

public class Product {

    private String barcode;

    private String name;

    private int price;

    private ProductCategory category;

    public Product(String barcode, String name, int price, ProductCategory category) {
        validate(barcode, name, price, category);
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }

    private void validate(String barcode, String name, int price, ProductCategory category) {
        if (barcode == null || barcode.trim().isEmpty()) {
            throw new IllegalArgumentException("Barcode cannot be empty!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be 0 or positive!");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be empty!");
        }
    }
}
