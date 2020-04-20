package simplefactory.farm;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CockTest {

    @Test
    public void speak() {
        //Given
        Cock cock = new Cock();
        //Then
        assertThat(cock.speak(), is("kukuriku"));
    }
}