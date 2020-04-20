package simplefactory.farm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class FarmAnimalFactoryTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void parameterShouldThrowExceptionWhenNull() throws IllegalArgumentException {
        // Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Animal type must not be empty!");
        // When
        new FarmAnimalFactory().create(null);
    }

    @Test
    public void parameterShouldThrowExceptionWhenEmpty() throws IllegalArgumentException {
        // Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Animal type must not be empty!");
        // When
        new FarmAnimalFactory().create("");
    }

    @Test
    public void parameterShouldThrowExceptionWhenUnrecognizable() throws IllegalArgumentException {
        // Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Unrecognizable animal type!");
        // When
        new FarmAnimalFactory().create("alma");
    }

    @Test
    public void createCock() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("cock");
        //Then
        assertThat(animal, instanceOf(Cock.class));
    }

    @Test
    public void createFrog() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("frog");
        //Then
        assertThat(animal, instanceOf(Frog.class));
    }

    @Test
    public void createHorse() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("horse");
        //Then
        assertThat(animal, instanceOf(Horse.class));
    }
}