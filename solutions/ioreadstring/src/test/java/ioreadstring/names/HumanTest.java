package ioreadstring.names;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanTest {

    Human h = new Human("John", "Doe");

    @Test
    void createHuman() {
        assertEquals("John", h.getFirstName());
        assertEquals("Doe", h.getLastName());
    }

}
