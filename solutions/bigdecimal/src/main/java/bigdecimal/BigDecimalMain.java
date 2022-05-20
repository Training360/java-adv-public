package bigdecimal;

import java.math.BigDecimal;
import java.util.Arrays;

public class BigDecimalMain {
    public static void main(String[] args) {
        BigDecimalOperations bigDecimalOperations = new BigDecimalOperations();
        System.out.println(bigDecimalOperations.calculateTax(BigDecimal.valueOf(1000.1234)));
        System.out.println(bigDecimalOperations.calculatePriceWithTax(BigDecimal.valueOf(1000.1234)));
        System.out.println(bigDecimalOperations.distributeBetweenDays(new BigDecimal("567.89"), 30));
        System.out.println(bigDecimalOperations.interestOnInterest(new BigDecimal("567.89"), new BigDecimal("0.2"), 5));
        System.out.println(bigDecimalOperations.roundHalfUpAndAddToLast(
                Arrays.asList(new BigDecimal("1.2311"), new BigDecimal("4.3222"), new BigDecimal("6.3433")),
                2
        ));

        // System.out.println(new BigDecimalOperations("1.1234").setScale(2));
    }
}
