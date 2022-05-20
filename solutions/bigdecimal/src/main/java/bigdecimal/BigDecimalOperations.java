package bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalOperations {

    public static final BigDecimal TAX = new BigDecimal("0.27");
    public static final int DEFAULT_SCALE = 2;

    public BigDecimal calculateTax(BigDecimal price) {
        return price.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP)
                .multiply(TAX)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculatePriceWithTax(BigDecimal price) {
        return price.setScale(2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.ONE.add(TAX))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal distributeBetweenDays(BigDecimal price, int numberOfDays) {
        return price.divide(new BigDecimal(numberOfDays), DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal interestOnInterest(BigDecimal principle, BigDecimal rate, int years) {
        return principle.multiply(BigDecimal.ONE.add(rate).pow(years)).setScale(2, RoundingMode.HALF_UP);
    }

    public List<BigDecimal> roundHalfUpAndAddToLast(List<BigDecimal> numbers, int scale) {
        List<BigDecimal> roundedNumbers = new ArrayList<>();
        BigDecimal rest = BigDecimal.ZERO;
        for (BigDecimal n: numbers) {
            BigDecimal rounded = n.setScale(scale, RoundingMode.HALF_UP);
            rest = rest.add(rounded.subtract(n));
            roundedNumbers.add(rounded);
        }
        roundedNumbers.add(rest);
        return roundedNumbers;
    }
}
