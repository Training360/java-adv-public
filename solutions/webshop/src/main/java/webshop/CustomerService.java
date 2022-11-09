package webshop;

import java.text.Collator;
import java.util.*;

public class CustomerService {

    private Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers() {
        return new HashSet<>(customers);
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be empty.");
        }
        if (customers.contains(customer)) {
            throw new IllegalArgumentException("Customer with e-mail address: " + customer.getEmail() + " is already registered.");
        }
        customers.add(customer);
    }

    public List<Customer> listCustomersByCategoryGiven(CustomerCategory category) {
        List<Customer> customersByCategory = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCategory() == category) {
                customersByCategory.add(customer);
            }
        }
        return customersByCategory;
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("No customer with e-mail address: " + email);
    }

    public List<Customer> listCustomersSortedByEmail() {
        List<Customer> sorted = new ArrayList<>(customers);
        Collections.sort(sorted);
        return sorted;
    }

    public List<String> listCustomerNamesSorted() {
        List<String> names = new ArrayList<>();
        for (Customer customer : customers) {
            names.add(customer.getName());
        }
        names.sort(Collator.getInstance(new Locale("hu", "HU")));
        return names;
    }
}
