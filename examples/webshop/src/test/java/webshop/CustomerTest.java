package webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;

    @BeforeEach
    void init() {
        customer = new Customer("Kiss József", "kissj@gmail.com");
    }

    @Test
    void testCreate() {
        assertEquals("Kiss József", customer.getName());
        assertEquals("kissj@gmail.com", customer.getEmail());
        assertEquals(CustomerCategory.SINGLE, customer.getCategory());
    }

    @Test
    void testEquals() {
        Customer otherCustomer = new Customer("Kissné Varga Júlia", "kissj@gmail.com");

        assertEquals(customer, otherCustomer);
    }

    @Test
    void testNaturalOrder() {
        List<Customer> customers = Arrays.asList(
                customer,
                new Customer("Elek János", "elekj@gmail.com"),
                new Customer("Balogh Tamás", "tbalogh@gmail.com"),
                new Customer("Molnár László", "molnarl@gmail.com")
        );
        Collections.sort(customers);

        assertEquals("Elek János", customers.get(0).getName());
        assertEquals("Kiss József", customers.get(1).getName());
        assertEquals("Molnár László", customers.get(2).getName());
        assertEquals("Balogh Tamás", customers.get(3).getName());
    }

    @Test
    void testSetCustomerToVip() {
        customer.setCustomerToVip();

        assertEquals(CustomerCategory.VIP, customer.getCategory());
    }

    @Test
    void testCreateWithEmptyName() {
        Exception nullName = assertThrows(IllegalArgumentException.class, () -> new Customer(null, "kissj@gmail.com"));
        assertEquals("Name or e-mail address cannot be empty!", nullName.getMessage());
        Exception emptyName = assertThrows(IllegalArgumentException.class, () -> new Customer("  \r   \n ", "kissj@gmail.com"));
        assertEquals("Name or e-mail address cannot be empty!", emptyName.getMessage());
    }

    @Test
    void testCreateWithEmptyEmail() {
        Exception nullEmail = assertThrows(IllegalArgumentException.class, () -> new Customer("Kiss József", null));
        assertEquals("Name or e-mail address cannot be empty!", nullEmail.getMessage());
        Exception emptyEmail = assertThrows(IllegalArgumentException.class, () -> new Customer("Kiss József", "   "));
        assertEquals("Name or e-mail address cannot be empty!", emptyEmail.getMessage());
    }
}