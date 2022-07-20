package webshop;

import java.util.Comparator;

public class OrderDateComparator implements Comparator<Order> {

    @Override
    public int compare(Order one, Order other) {
        return other.getTimeOfOrder().compareTo(one.getTimeOfOrder());
    }
}
