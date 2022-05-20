package simplefactory.farm;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CockTest {

    @Test
    public void speak() {
        //Given
        Cock cock = new Cock();
        //Then
        assertEquals("kukuriku", cock.speak());
    }
}