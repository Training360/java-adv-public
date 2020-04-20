package simplefactory.farm;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FrogTest {

    @Test
    public void speak() {
        //Given
        Frog frog = new Frog();
        //Then
        assertThat(frog.speak(), is("brekeke"));
    }
}