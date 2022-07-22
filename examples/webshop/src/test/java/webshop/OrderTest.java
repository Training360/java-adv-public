package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Customer customer;

    Product product;

    Map<Product, Integer> cart;

    Order order;

    @BeforeEach
    void init() {
        customer = new Customer("Kiss József", "kissj@gmail.com");
        cart = new HashMap<>();
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        cart.put(product, 2);
        order = new Order(3L, LocalDateTime.of(2022, 7, 17, 11, 0), customer, cart);
    }

    @Test
    void testCreate() {
        assertEquals(3L, order.getId());
        assertEquals(LocalDate.of(2022, 7, 17), order.getTimeOfOrder().toLocalDate());
        assertEquals("kissj@gmail.com", order.getCustomer().getEmail());
        assertEquals(1, order.getCart().size());
        assertEquals(2, order.getCart().get(product));
    }

    @Test
    void testAddCartItemInAnInvalidWay() {
        order.getCart().put(new Product("6789", "Kisautó", 500, ProductCategory.TOY), 5);

        assertEquals(1, order.getCart().size());
    }

    @Test
    void testGetTotalAmountForSingleCustomer() {
        product = new Product("3456", "Labda", 1500, ProductCategory.TOY);
        cart.put(product, 3);
        int expected = order.getTotalAmount();

        assertEquals(29500, expected);
    }

    @Test
    void testGetTotalAmountForVipCustomer() {
        product = new Product("3456", "Labda", 1500, ProductCategory.TOY);
        cart.put(product, 3);
        customer.setCustomerToVip();
        int expected = order.getTotalAmount();

        assertEquals(26550, expected);
    }

    @Test
    void testHasCustomerBoughtProduct() {
        Product otherProduct = new Product("3456", "Labda", 1500, ProductCategory.TOY);

        assertEquals(true, order.hasCustomerBoughtProduct(product));
        assertEquals(false, order.hasCustomerBoughtProduct(otherProduct));
    }

    @Test
    void testCreateWithWrongId() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Order(0, LocalDateTime.now(), customer, cart));
        assertEquals("Number 0 is not a valid id!", ex.getMessage());
    }

    @Test
    void testCreateWithNullTimeOfOrder() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Order(3L, null, customer, cart));
        assertEquals("Time of order cannot be empty!", ex.getMessage());
    }

    @Test
    void testCreateWithNullCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Order(3L, LocalDateTime.now(),null, cart));
        assertEquals("Customer cannot be empty!", ex.getMessage());
    }

    @Test
    void testCreateWithEmptyCart() {
        Exception nullCart = assertThrows(IllegalArgumentException.class,
                () -> new Order(3L, LocalDateTime.now(), customer, null));
        assertEquals("Cart cannot be empty!", nullCart.getMessage());
        Exception emptyCart = assertThrows(IllegalArgumentException.class,
                () -> new Order(3L, LocalDateTime.now(), customer, new HashMap<>()));
        assertEquals("Cart cannot be empty!", emptyCart.getMessage());
    }
}