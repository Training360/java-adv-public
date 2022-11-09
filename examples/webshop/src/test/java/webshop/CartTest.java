package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Customer customer;

    Cart cart;

    Product product;

    @BeforeEach
    void init() {
        customer = new Customer("Kiss József", "kissj@gmail.com");
        cart = new Cart(customer);
    }

    @Test
    void testCreate() {
        assertEquals("Kiss József", cart.getCustomer().getName());
        assertEquals("kissj@gmail.com", cart.getCustomer().getEmail());
        assertEquals(0, cart.getProducts().size());
    }

    @Test
    void testAddOneCartItem() {
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        cart.addCartItem(product, 2);

        assertEquals(1, cart.getProducts().size());
        assertEquals(true, cart.getProducts().containsKey(product));
        assertEquals(2, cart.getProducts().get(product));
    }

    @Test
    void testAddSameCartItemTwice() {
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        cart.addCartItem(product, 2);
        cart.addCartItem(product, 3);

        assertEquals(1, cart.getProducts().size());
        assertEquals(5, cart.getProducts().get(product));
    }

    @Test
    void testAddMoreCartItems() {
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        cart.addCartItem(product, 2);
        Product otherProduct = new Product("3456", "Labda", 1500, ProductCategory.TOY);
        cart.addCartItem(otherProduct, 3);

        assertEquals(2, cart.getProducts().size());
        assertEquals(true, cart.getProducts().containsKey(product));
        assertEquals(2, cart.getProducts().get(product));
        assertEquals(true, cart.getProducts().containsKey(otherProduct));
        assertEquals(3, cart.getProducts().get(otherProduct));
    }

    @Test
    void testAddCartItemInAnInvalidWay() {
        cart.getProducts().put(new Product("6789", "Kisautó", 500, ProductCategory.TOY), 5);

        assertEquals(0, cart.getProducts().size());
    }

    @Test
    void testAddCartItemWithNullProduct() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cart.addCartItem(product, 2));
        assertEquals("Product cannot be empty.", ex.getMessage());
    }

    @Test
    void testAddCartItemWithWrongQuantity() {
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cart.addCartItem(product, 0));
        assertEquals("Quantity cannot be 0 or a negative number", ex.getMessage());
    }

    @Test
    void testEquals() {
        Cart otherCart = new Cart(customer);

        assertEquals(cart, otherCart);
    }

    @Test
    void testCreateWithNullCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Cart(null));
        assertEquals("Customer cannot be empty!", ex.getMessage());
    }
}