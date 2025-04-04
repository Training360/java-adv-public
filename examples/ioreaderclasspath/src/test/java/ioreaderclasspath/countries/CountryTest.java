package ioreaderclasspath.countries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    Country c = new Country("Hungary", 7);

    @Test
    public void createCountry() {
        assertEquals("Hungary", c.getName());
        assertEquals(7, c.getBorderCountries());
    }

}
