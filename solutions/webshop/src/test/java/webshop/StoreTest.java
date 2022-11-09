package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    Store store;

    @BeforeEach
    void init() {
        store = new Store();
        store.addProduct(new Product("1234", "Lego", 12500, ProductCategory.TOY));
        store.addProduct(new Product("2345", "Gárdonyi Géza: Egri csillagok", 3600, ProductCategory.BOOK));
        store.addProduct(new Product("3456", "Labda", 1500, ProductCategory.TOY));
        store.addProduct(new Product("4567", "Labda", 1500, ProductCategory.TOY));
        store.addProduct(new Product("5678", "Molnár Ferenc: A Pál utcai fiúk", 3500, ProductCategory.BOOK));
        store.addProduct(new Product("6789", "Kisautó", 500, ProductCategory.TOY));
        store.addProduct(new Product("7890", "Férfi nadrág", 8500, ProductCategory.CLOTHING));
    }

    @Test
    void testAddProduct() {
        assertEquals(7, store.getProducts().size());
    }

    @Test
    void testAddProductInAnInvalidWay() {
        store.getProducts().add(new Product("12345", "Baba", 2500, ProductCategory.TOY));

        assertEquals(7, store.getProducts().size());
    }

    @Test
    void testAddSameProduct() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> store.addProduct(new Product("1234", "Baba", 2500, ProductCategory.TOY)));
        assertEquals("Product with barcode: 1234 is already registered.", ex.getMessage());
        assertEquals(7, store.getProducts().size());
    }

    @Test
    void testAddNullProduct() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> store.addProduct(null));
        assertEquals("Product cannot be empty!", ex.getMessage());
    }

    @Test
    void testGetProductByBarcode() {
        Product expected = store.getProductByBarcode("2345");

        assertEquals("Gárdonyi Géza: Egri csillagok", expected.getName());
        assertEquals(3600, expected.getPrice());
    }

    @Test
    void testGetProductByNotExistingBarcode() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> store.getProductByBarcode("1"));
        assertEquals("No product with barcode: 1", ex.getMessage());
    }

    @Test
    void testGetProductsByCategory() {
        Map<ProductCategory, List<Product>> expected = store.getProductsByCategory();

        assertEquals(3, expected.size());
        assertEquals(4, expected.get(ProductCategory.TOY).size());
        assertEquals(2, expected.get(ProductCategory.BOOK).size());
        assertEquals(1, expected.get(ProductCategory.CLOTHING).size());
    }

    @Test
    void testGetCheapestProductByCategory() {
        Product expected = store.getCheapestProductByCategory(ProductCategory.TOY);

        assertEquals("6789", expected.getBarcode());
        assertEquals("Kisautó", expected.getName());
        assertEquals(500, expected.getPrice());
        assertEquals(ProductCategory.TOY, expected.getCategory());
    }

    @Test
    void testListProductsSortedByPrice() {
        List<Product> expected = store.listProductsSortedByPrice();

        assertEquals(7, expected.size());
        assertEquals("Kisautó", expected.get(0).getName());
        assertEquals("Molnár Ferenc: A Pál utcai fiúk", expected.get(3).getName());
        assertEquals("Lego", expected.get(6).getName());
    }

    @Test
    void testListProductsSortedByName() {
        List<Product> expected = store.listProductsSortedByName();

        assertEquals(7, expected.size());
        assertEquals("Férfi nadrág", expected.get(0).getName());
        assertEquals("Lego", expected.get(5).getName());
        assertEquals("Molnár Ferenc: A Pál utcai fiúk", expected.get(6).getName());
    }
}