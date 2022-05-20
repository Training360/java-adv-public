package formatnumberformat;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PriceConverter {

    private static final Locale LOCALE_HU = new Locale("hu", "HU");

    public String toString(double price) {
        NumberFormat numberFormat = NumberFormat.getInstance(LOCALE_HU);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        return numberFormat.format(price);
    }

    public String toCurrencyString(double price) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(LOCALE_HU);
        return numberFormat.format(price);
    }

    public double toDouble(String s) {
        NumberFormat numberFormat = NumberFormat.getInstance(LOCALE_HU);
        try {
            return numberFormat.parse(s).doubleValue();
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Can not parse string: " + s, pe);
        }
    }

}
