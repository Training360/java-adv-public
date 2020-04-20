package simplefactory.farm;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HorseTest {

    @Test
    public void speak() {
        //Given
        Horse horse = new Horse();
        //Then
        assertThat(horse.speak(), is("nyihaha"));
    }
}