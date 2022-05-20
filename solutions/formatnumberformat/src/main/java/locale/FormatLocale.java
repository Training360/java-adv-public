package locale;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatLocale {

    private Locale defaultLocale;

    public FormatLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public String formatCurrency(double number, Locale locale) {
        if (locale == null){
            throw new NullPointerException("Locale must not be null");
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(number);
    }

    public String formatCurrency(double number) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(defaultLocale);
        return nf.format(number);
    }

    public String formatCurrencyByLanguage(double number, String language, String country) {
        Locale locale = new Locale(language, country);
        if (!localePresent(locale) || isEmpty(language) || isEmpty(country)) {
            throw new IllegalArgumentException("Incorrect arguments!");
        }
        return formatCurrency(number, locale);
    }

    public String formatPercentage(double number, Locale locale) {
        if (locale == null){
            throw new NullPointerException("Locale must not be null");        }

        NumberFormat nf = NumberFormat.getPercentInstance(locale);
        return nf.format(number);
    }

    public String formatPercentage(double number) {

        NumberFormat nf = NumberFormat.getPercentInstance(defaultLocale);
        return nf.format(number);
    }

    public String formatPercentageByLanguage(double number, String language, String country) {
        Locale locale = new Locale(language, country);
        if (!localePresent(locale) || isEmpty(language) || isEmpty(country)) {
            throw new IllegalArgumentException("Incorrect arguments!");
        }
        return formatPercentage(number, locale);
    }

    public String formatNumber(double number, Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.CEILING);
        return nf.format(number);
    }

    private boolean localePresent(Locale locale) {
        for (Locale l : NumberFormat.getAvailableLocales()) {
            if (l.equals(locale)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
