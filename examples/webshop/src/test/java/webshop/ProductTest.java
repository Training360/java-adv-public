package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;

    @BeforeEach
    void init() {
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
    }

    @Test
    void testCreate() {
        assertEquals("1234", product.getBarcode());
        assertEquals("Lego", product.getName());
        assertEquals(12500, product.getPrice());
        assertEquals(ProductCategory.TOY, product.getCategory());
    }

    @Test
    void testEquals() {
        Product otherProduct = new Product("1234", "Gárdonyi Géza: Egri csillagok", 3600, ProductCategory.BOOK);

        assertEquals(product, otherProduct);
    }

    @Test
    void testCreateWithEmptyBarcode() {
        Exception nullBarcode = assertThrows(IllegalArgumentException.class,
                () -> new Product(null, "Lego", 12500, ProductCategory.TOY));
        assertEquals("Barcode cannot be empty!", nullBarcode.getMessage());
        Exception emptyBarcode = assertThrows(IllegalArgumentException.class,
                () -> new Product("   ", "Lego", 12500, ProductCategory.TOY));
        assertEquals("Barcode cannot be empty!", emptyBarcode.getMessage());
    }

    @Test
    void testCreateWithEmptyName() {
        Exception nullName = assertThrows(IllegalArgumentException.class,
                () -> new Product("1234", null, 12500, ProductCategory.TOY));
        assertEquals("Name cannot be empty!", nullName.getMessage());
        Exception emptyName = assertThrows(IllegalArgumentException.class,
                () -> new Product("1234", " \n  \r  ", 12500, ProductCategory.TOY));
        assertEquals("Name cannot be empty!", emptyName.getMessage());
    }

    @Test
    void testCreateWithNegativePrice() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Product("1234", "Lego", -1, ProductCategory.TOY));
        assertEquals("Price must be 0 or positive!", ex.getMessage());
    }

    @Test
    void testCreateWithNullCategory() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Product("1234", "Lego", 12500, null));
        assertEquals("Category cannot be empty!", ex.getMessage());
    }
}