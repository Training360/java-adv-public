package dpintro.reptile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CrocodileTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void lessEnergyShouldThrowException() throws IllegalArgumentException {
        // Given
        Crocodile crocodile = new Crocodile(15);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Not enough energy to move!");
        // When
        crocodile.walk();
    }

    @Test
    public void testSwim() {
        //Given
        Crocodile crocodile = new Crocodile(100);
        //When
        crocodile.swim();
        //Then
        assertThat(crocodile.getEnergy(), is(95));
    }

    @Test
    public void testWalk() {
        //Given
        Crocodile crocodile = new Crocodile(100);
        //When
        crocodile.walk();
        //Then
        assertThat(crocodile.getEnergy(), is(80));
    }
}