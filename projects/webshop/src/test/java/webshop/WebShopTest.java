package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WebShopTest {

    Product product;

    Customer customer;

    Store store;

    CustomerService customerService;

    WebShop webShop;

    @BeforeEach
    void init() {
        customer = new Customer("Kiss József", "kissj@gmail.com");
        store = new Store();
        customerService = new CustomerService();
        webShop = new WebShop(store, customerService);
        product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        webShop.addProduct(product);
        webShop.addProduct(new Product("2345", "Gárdonyi Géza: Egri csillagok", 3600, ProductCategory.BOOK));
        webShop.addProduct(new Product("3456", "Labda", 1500, ProductCategory.TOY));
        webShop.addCustomer(customer);
        webShop.addCustomer(new Customer("Elek János", "elekj@gmail.com"));
    }

    @Test
    void testCreate() {
        assertEquals(3, webShop.getStore().getProducts().size());
        assertEquals(2, webShop.getCustomerService().getCustomers().size());
        assertEquals(0, webShop.getCarts().size());
        assertEquals(0, webShop.getOrders().size());
    }

    @Test
    void testAddCartInAnInvalidWay() {
        webShop.getCarts().add(new Cart(customer));

        assertEquals(0, webShop.getCarts().size());
    }

    @Test
    void testAddOrderInAnInvalidWay() {
        Map<Product, Integer> cart = new HashMap<>();
        Product product = new Product("1234", "Lego", 12500, ProductCategory.TOY);
        cart.put(product, 2);
        webShop.getOrders().add(new Order(3L, LocalDateTime.now(), customer, cart));

        assertEquals(0, webShop.getOrders().size());
    }

    @Test
    void testCreateWithNullStore() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new WebShop(null, customerService));
        assertEquals("Store cannot be empty!", ex.getMessage());
    }

    @Test
    void testCreateWithNullCustomerService() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new WebShop(store, null));
        assertEquals("Customer service cannot be empty!", ex.getMessage());
    }

    @Test
    void testBeginShopping() {
        webShop.beginShopping("kissj@gmail.com");
        List<Cart> carts = new ArrayList<>(webShop.getCarts());

        assertEquals(1, webShop.getCarts().size());
        assertEquals("Kiss József", carts.get(0).getCustomer().getName());
        assertEquals(0, carts.get(0).getProducts().size());
    }

    @Test
    void testBeginShoppingWithSameCustomerTwice() {
        webShop.beginShopping("kissj@gmail.com");

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.beginShopping("kissj@gmail.com"));
        assertEquals("Customer with e-mail address: kissj@gmail.com has already began shopping!", ex.getMessage());
    }

    @Test
    void testBeginShoppingWithNotExistingCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.beginShopping("xy@z.com"));
        assertEquals("No customer with e-mail address: xy@z.com", ex.getMessage());
    }

    @Test
    void testAddCartItem() {
        webShop.beginShopping("kissj@gmail.com");
        webShop.addCartItem("kissj@gmail.com", "1234", 2);
        List<Cart> carts = new ArrayList<>(webShop.getCarts());

        assertEquals(1, webShop.getCarts().size());
        assertEquals("Kiss József", carts.get(0).getCustomer().getName());
        assertEquals(1, carts.get(0).getProducts().size());
        assertEquals(true, carts.get(0).getProducts().containsKey(product));
        assertEquals(2, carts.get(0).getProducts().get(product));
    }

    @Test
    void testAddCartItemWithInvalidQuantity() {
        webShop.beginShopping("kissj@gmail.com");

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.addCartItem("kissj@gmail.com", "1234", 0));
        assertEquals("Quantity cannot be 0 or a negative number", ex.getMessage());
    }

    @Test
    void testAddCartItemNotExistingCart() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.addCartItem("kissj@gmail.com", "1234", 2));
        assertEquals("Customer with e-mail address kissj@gmail.com does not have an actual cart yet.", ex.getMessage());
    }

    @Test
    void testRejectCart() {
        webShop.beginShopping("kissj@gmail.com");
        webShop.addCartItem("kissj@gmail.com", "1234", 2);
        webShop.rejectCart("kissj@gmail.com");

        assertEquals(0, webShop.getCarts().size());
    }

    @Test
    void testOrder() {
        webShop.beginShopping("kissj@gmail.com");
        webShop.addCartItem("kissj@gmail.com", "1234", 2);
        long id = webShop.order("kissj@gmail.com", LocalDateTime.now());

        assertEquals(1, id);
        assertEquals(1, webShop.getOrders().size());
        assertEquals(0, webShop.getCarts().size());
        assertEquals(CustomerCategory.SINGLE, customer.getCategory());
    }

    @Test
    void testOrderVip() {
        for (int i = 1; i <= 5; i++) {
            webShop.beginShopping("kissj@gmail.com");
            webShop.addCartItem("kissj@gmail.com", "1234", 2);
            webShop.order("kissj@gmail.com", LocalDateTime.now());
        }

        assertEquals(CustomerCategory.VIP, customer.getCategory());
    }

    @Test
    void testOrderWithNotExistingCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.order("xy@z.com",  LocalDateTime.now()));
        assertEquals("No customer with e-mail address: xy@z.com", ex.getMessage());
    }

    @Test
    void testOrderWithNotExistingCart() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.order("kissj@gmail.com", LocalDateTime.now()));
        assertEquals("Customer with e-mail address kissj@gmail.com does not have an actual cart yet.", ex.getMessage());
    }

    @Test
    void testGetCustomersByProduct() {
        addOrders();
        assertEquals(1, webShop.getCustomersByProduct("1234").size());
        assertEquals(2, webShop.getCustomersByProduct("2345").size());
    }

    @Test
    void testGetTotalAmounts() {
        addOrders();
        Map<Long, Integer> expected = webShop.getTotalAmounts();

        assertEquals(25000, webShop.getTotalAmounts().get(1L));
        assertEquals(3600, webShop.getTotalAmounts().get(2L));
        assertEquals(44700, webShop.getTotalAmounts().get(3L));
    }

    @Test
    void testGetCustomerWithMaxTotalAmount() {
        addOrders();
        Customer expected = webShop.getCustomerWithMaxTotalAmount();

        assertEquals("Kiss József", expected.getName());
    }

    @Test
    void testGetCustomerWithMaxTotalAmountWithEmptyList() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> webShop.getCustomerWithMaxTotalAmount());
        assertEquals("No such customer.", ex.getMessage());
    }

    @Test
    void testListOrdersSortedByTotalAmounts() {
        addOrders();
        List<Order> expected = webShop.listOrdersSortedByTotalAmounts();

        assertEquals(3, expected.size());
        assertEquals(44700, expected.get(0).getTotalAmount());
        assertEquals(25000, expected.get(1).getTotalAmount());
        assertEquals(3600, expected.get(2).getTotalAmount());
    }

    @Test
    void testListOrdersSortedByDate() {
        addOrders();
        List<Order> expected = webShop.listOrdersSortedByDate();

        assertEquals(3, expected.size());
        assertEquals(44700, expected.get(0).getTotalAmount());
        assertEquals(3600, expected.get(1).getTotalAmount());
        assertEquals(25000, expected.get(2).getTotalAmount());
    }

    @Test
    void testHasCustomerBoughtProduct() {
        addOrders();

        assertEquals(true, webShop.hasCustomerBoughtProduct("kissj@gmail.com", "1234"));
        assertEquals(false, webShop.hasCustomerBoughtProduct("elekj@gmail.com", "1234"));
    }

    private void addOrders() {
        webShop.beginShopping("kissj@gmail.com");
        webShop.addCartItem("kissj@gmail.com", "1234", 2);
        webShop.order("kissj@gmail.com", LocalDateTime.of(2022, 7, 15, 9, 0));
        webShop.beginShopping("elekj@gmail.com");
        webShop.addCartItem("elekj@gmail.com", "2345", 1);
        webShop.order("elekj@gmail.com", LocalDateTime.of(2022, 7, 16, 10, 0));
        webShop.beginShopping("kissj@gmail.com");
        webShop.addCartItem("kissj@gmail.com", "2345", 2);
        webShop.addCartItem("kissj@gmail.com", "1234", 3);
        webShop.order("kissj@gmail.com", LocalDateTime.of(2022, 7, 17, 11, 0));
    }
}