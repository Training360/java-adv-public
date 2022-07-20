package webshop;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private long id;

    private LocalDateTime timeOfOrder;

    private Customer customer;

    private Map<Product, Integer> cart;

    public Order(long id, LocalDateTime timeOfOrder, Customer customer, Map<Product, Integer> cart) {
        validate(id, timeOfOrder, customer, cart);
        this.id = id;
        this.timeOfOrder = timeOfOrder;
        this.customer = customer;
        this.cart = cart;
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
        if (customer.getCategory() == CustomerCategory.VIP) {
            totalAmount = totalAmount * (100 - CustomerCategory.VIP.getDiscount()) / 100;
        }
        return totalAmount;
    }

    public boolean hasCustomerBoughtProduct(Product product) {
        return cart.containsKey(product);
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getCart() {
        return new HashMap<>(cart);
    }

    private void validate(long id, LocalDateTime timeOfOrder, Customer customer, Map<Product, Integer> cart) {
        if (id <= 0) {
            throw new IllegalArgumentException("Number " + id + " is not a valid id!");
        }
        if (timeOfOrder == null) {
            throw new IllegalArgumentException("Time of order cannot be empty!");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be empty!");
        }
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be empty!");
        }
    }
}
