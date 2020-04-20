package simplefactory.farm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FarmComponentTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void emptyParameterShouldThrowException() throws IllegalArgumentException {
        // Given
        Farm farm = new Farm(new FarmAnimalFactory());
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Animal type must not be empty!");
        // When
        farm.newAnimalArrived("");
    }

    @Test
    public void getAnimalVoicesWithOneFrog() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        //Then
        assertThat(farm.getAnimalVoices().size(), is(1));
        assertThat(farm.getAnimalVoices().get(0), is("brekeke"));
    }

    @Test
    public void getAnimalVoicesWithTwoFrog() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        farm.newAnimalArrived("frog");
        //Then
        assertThat(farm.getAnimalVoices().size(), is(1));
        assertThat(farm.getAnimalVoices().get(0), is("brekeke"));
    }

    @Test
    public void getAnimalVoicesWithOneFrogOneHorse() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        farm.newAnimalArrived("horse");
        //Then
        assertThat(farm.getAnimalVoices().size(), is(2));
        assertThat(farm.getAnimalVoices().get(0), is("brekeke"));
        assertThat(farm.getAnimalVoices().get(1), is("nyihaha"));
    }

    @Test
    public void getAnimalVoicesComplexTest() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        farm.newAnimalArrived("cock");
        farm.newAnimalArrived("horse");
        farm.newAnimalArrived("horse");
        farm.newAnimalArrived("horse");
        //Then
        assertThat(farm.getAnimalVoices().size(), is(3));
        assertThat(farm.getAnimalVoices().get(0), is("brekeke"));
        assertThat(farm.getAnimalVoices().get(1), is("kukuriku"));
        assertThat(farm.getAnimalVoices().get(2), is("nyihaha"));
    }
}