package dpintro.reptile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrocodileTest {


    @Test
    public void lessEnergyShouldThrowException() throws IllegalArgumentException {
        Crocodile crocodile = new Crocodile(15);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            crocodile.walk();
        });
        assertEquals("Not enough energy to move!", ex.getMessage());
    }

    @Test
    public void testSwim() {
        //Given
        Crocodile crocodile = new Crocodile(100);
        //When
        crocodile.swim();
        //Then
        assertEquals(95, crocodile.getEnergy());
    }

    @Test
    public void testWalk() {
        //Given
        Crocodile crocodile = new Crocodile(100);
        //When
        crocodile.walk();
        //Then
        assertEquals(80, crocodile.getEnergy());
    }
}