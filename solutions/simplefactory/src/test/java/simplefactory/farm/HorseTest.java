package simplefactory.farm;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseTest {

    @Test
    public void speak() {
        //Given
        Horse horse = new Horse();
        //Then
        assertEquals("nyihaha", horse.speak());
    }
}