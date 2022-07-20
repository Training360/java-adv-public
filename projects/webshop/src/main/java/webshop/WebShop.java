package webshop;

import java.time.LocalDateTime;
import java.util.*;

public class WebShop {

    private Store store;

    private CustomerService customerService;

    private Set<Cart> carts = new HashSet<>();

    private List<Order> orders = new ArrayList<>();

    public WebShop(Store store, CustomerService customerService) {
        validate(store, customerService);
        this.store = store;
        this.customerService = customerService;
    }

    public void addProduct(Product product) {
        store.addProduct(product);
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public void beginShopping(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        Cart cart = new Cart(customer);
        if (carts.contains(cart)) {
            throw new IllegalArgumentException("Customer with e-mail address: " + email + " has already began shopping!");
        }
        carts.add(cart);
    }

    public void addCartItem(String email, String barcode, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be 0 or a negative number");
        }
        Cart cart = getCartByCustomerEmail(email);
        Product product = store.getProductByBarcode(barcode);
        cart.addCartItem(product, quantity);
    }

    public void rejectCart(String email) {
        Cart cart = getCartByCustomerEmail(email);
        carts.remove(cart);
    }

    public long order(String email, LocalDateTime timeOfOrder) {
        long id = orders.size() + 1;
        Cart cart = getCartByCustomerEmail(email);
        Customer customer = cart.getCustomer();
        Order order = new Order(id, timeOfOrder, customer, cart.getProducts());
        orders.add(order);
        carts.remove(cart);
        if (checkIfCustomerCanBeVip(customer)) {
            customer.setCustomerToVip();
        }
        return id;
    }

    public Set<Customer> getCustomersByProduct(String barcode) {
        Set<Customer> customersBoughtThisProduct = new HashSet<>();
        Product product = store.getProductByBarcode(barcode);
        for (Order order : orders) {
            if (order.getCart().containsKey(product)) {
                customersBoughtThisProduct.add(order.getCustomer());
            }
        }
        return customersBoughtThisProduct;
    }

    public Map<Long, Integer> getTotalAmounts() {
        Map<Long, Integer> totalAmounts = new HashMap<>();
        for (Order order : orders) {
            long id = order.getId();
            int totalAmount = order.getTotalAmount();
            totalAmounts.put(id, totalAmount);
        }
        return totalAmounts;
    }

    public Customer getCustomerWithMaxTotalAmount() {
        if (orders.size() == 0) {
            throw new IllegalArgumentException("No such customer.");
        }
        Customer customer = orders.get(0).getCustomer();
        int maximalAmount = orders.get(0).getTotalAmount();
        for (Order order : orders) {
            if (order.getTotalAmount() > maximalAmount) {
                customer = order.getCustomer();
                maximalAmount = order.getTotalAmount();
            }
        }
        return customer;
    }

    public List<Order> listOrdersSortedByTotalAmounts() {
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(new OrderTotalAmountComparator());
        return sorted;
    }

    public List<Order> listOrdersSortedByDate() {
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(new OrderDateComparator());
        return sorted;
    }

    public boolean hasCustomerBoughtProduct(String email, String barcode) {
        Customer customer = customerService.getCustomerByEmail(email);
        Product product = store.getProductByBarcode(barcode);
        for (Order order : orders) {
            if (order.getCustomer().equals(customer) && order.hasCustomerBoughtProduct(product)) {
                return true;
            }
        }
        return false;
    }

    public Store getStore() {
        return store;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Set<Cart> getCarts() {
        return new HashSet<>(carts);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    private void validate(Store store, CustomerService customerService) {
        if (store == null) {
            throw new IllegalArgumentException("Store cannot be empty!");
        }
        if (customerService == null) {
            throw new IllegalArgumentException("Customer service cannot be empty!");
        }
    }

    private Cart getCartByCustomerEmail(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        for (Cart cart : carts) {
            if (cart.getCustomer().equals(customer)) {
                return cart;
            }
        }
        throw new IllegalArgumentException("Customer with e-mail address " + email + " does not have an actual cart yet.");
    }

    private boolean checkIfCustomerCanBeVip(Customer customer) {
        int ordersOfCustomer = 0;
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                ordersOfCustomer++;
            }
        }
        return ordersOfCustomer >= 5;
    }
}
