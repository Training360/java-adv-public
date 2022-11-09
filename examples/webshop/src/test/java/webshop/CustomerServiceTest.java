package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    CustomerService customerService;

    @BeforeEach
    void init() {
        customerService = new CustomerService();
        customerService.addCustomer(new Customer("Kiss József", "kissj@gmail.com"));
        customerService.addCustomer(new Customer("Elek János", "elekj@gmail.com"));
        customerService.addCustomer(new Customer("Balogh Tamás", "tbalogh@gmail.com"));
        customerService.addCustomer(new Customer("Molnár László", "molnarl@gmail.com"));
    }

    @Test
    void testAddCustomer() {
        assertEquals(4, customerService.getCustomers().size());
    }

    @Test
    void testAddCustomerInAnInvalidWay() {
        customerService.getCustomers().add(new Customer("Varga Júlia", "vj@gmail.com"));

        assertEquals(4, customerService.getCustomers().size());
    }

    @Test
    void testAddSameCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> customerService.addCustomer(new Customer("Kissné Varga Júlia", "kissj@gmail.com")));
        assertEquals("Customer with e-mail address: kissj@gmail.com is already registered.", ex.getMessage());
        assertEquals(4, customerService.getCustomers().size());
    }

    @Test
    void testAddNullCustomer() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> customerService.addCustomer(null));
        assertEquals("Customer cannot be empty.", ex.getMessage());
    }

    @Test
    void testListCustomersByCategoryGiven() {
        Customer customer = new Customer("Nagy Béla", "nagyb@gmail.com");
        customer.setCustomerToVip();
        customerService.addCustomer(customer);
        List<Customer> expected = customerService.listCustomersByCategoryGiven(CustomerCategory.SINGLE);

        assertEquals(5, customerService.getCustomers().size());
        assertEquals(4, expected.size());
    }

    @Test
    void testGetCustomerByEmail() {
        Customer expected = customerService.getCustomerByEmail("elekj@gmail.com");

        assertEquals("Elek János", expected.getName());
    }

    @Test
    void testGetCustomerWithNotExistingEmail() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> customerService.getCustomerByEmail("xy@z.com"));
        assertEquals("No customer with e-mail address: xy@z.com", ex.getMessage());
    }

    @Test
    void testListCustomersSortedByEmail() {
        List<Customer> expected = customerService.listCustomersSortedByEmail();

        assertEquals(4, expected.size());
        assertEquals("Elek János", expected.get(0).getName());
        assertEquals("Kiss József", expected.get(1).getName());
        assertEquals("Molnár László", expected.get(2).getName());
        assertEquals("Balogh Tamás", expected.get(3).getName());
    }

    @Test
    void testListCustomerNamesSorted() {
        List<String> expected = customerService.listCustomerNamesSorted();

        assertEquals("Balogh Tamás", expected.get(0));
        assertEquals("Elek János", expected.get(1));
        assertEquals("Kiss József", expected.get(2));
        assertEquals("Molnár László", expected.get(3));
    }
}