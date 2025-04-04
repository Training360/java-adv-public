package ioreaderclasspath.countries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryStatisticsTest {

    CountryStatistics countryStatistics = new CountryStatistics();

    @Test
    void createList() {
        assertEquals(0, countryStatistics.getCountries().size());
        countryStatistics.getCountries().add(new Country("Test", 3));
        assertEquals(0, countryStatistics.getCountries().size());
    }


    @Test
    void readFromFile() {
        assertEquals(0, countryStatistics.getCountries().size());
        countryStatistics.readFromFile("country.txt");

        assertEquals(8, countryStatistics.getCountries().size());

        assertEquals("Austria", countryStatistics.getCountries().get(1).getName());
        assertEquals(1, countryStatistics.getCountries().get(5).getBorderCountries());
    }

    @Test
    void numberOfCountries() {
        assertEquals(0, countryStatistics.numberOFCountries());
        countryStatistics.readFromFile("country.txt");
        assertEquals(8, countryStatistics.numberOFCountries());
    }

    @Test
    void mostBorderCountries() {
        countryStatistics.readFromFile("country.txt");
        assertEquals("Germany", countryStatistics.mostBorderCountries().getName());
        assertEquals(8, countryStatistics.mostBorderCountries().getBorderCountries());
    }
}
