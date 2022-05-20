package simplefactory.farm;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FarmAnimalFactoryTest {


    @Test
    public void parameterShouldThrowExceptionWhenNull() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new FarmAnimalFactory().create(null));
        assertEquals("Animal type must not be empty!", ex.getMessage());
    }

    @Test
    public void parameterShouldThrowExceptionWhenEmpty() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new FarmAnimalFactory().create(""));
        assertEquals("Animal type must not be empty!", ex.getMessage());
    }

    @Test
    public void parameterShouldThrowExceptionWhenUnrecognizable() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new FarmAnimalFactory().create("alma"));
        assertEquals("Unrecognizable animal type!", ex.getMessage());
    }

    @Test
    public void createCock() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("cock");
        //Then
        assertTrue(animal instanceof Cock);
    }

    @Test
    public void createFrog() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("frog");
        //Then
        assertTrue(animal instanceof Frog);
    }

    @Test
    public void createHorse() {
        //Given
        FarmAnimalFactory animalFactory = new FarmAnimalFactory();
        Animal animal = animalFactory.create("horse");
        //Then
        assertTrue(animal instanceof Horse);
    }
}