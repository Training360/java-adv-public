package simplefactory.farm;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrogTest {

    @Test
    public void speak() {
        //Given
        Frog frog = new Frog();
        //Then
        assertEquals("brekeke", frog.speak());
    }
}