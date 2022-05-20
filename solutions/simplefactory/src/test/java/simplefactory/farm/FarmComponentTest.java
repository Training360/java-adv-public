package simplefactory.farm;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FarmComponentTest {


    @Test
    public void emptyParameterShouldThrowException() throws IllegalArgumentException {
        Farm farm = new Farm(new FarmAnimalFactory());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> farm.newAnimalArrived(""));
        assertEquals("Animal type must not be empty!", ex.getMessage());
    }

    @Test
    public void getAnimalVoicesWithOneFrog() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        //Then
        assertEquals(1, farm.getAnimalVoices().size());
        assertEquals("brekeke", farm.getAnimalVoices().get(0));
    }

    @Test
    public void getAnimalVoicesWithTwoFrog() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        farm.newAnimalArrived("frog");
        //Then
        assertEquals(1, farm.getAnimalVoices().size());
        assertEquals("brekeke", farm.getAnimalVoices().get(0));
    }

    @Test
    public void getAnimalVoicesWithOneFrogOneHorse() {
        //Given
        Farm farm = new Farm(new FarmAnimalFactory());
        //When
        farm.newAnimalArrived("frog");
        farm.newAnimalArrived("horse");
        //Then
        assertEquals(2, farm.getAnimalVoices().size());
        assertEquals("brekeke", farm.getAnimalVoices().get(0));
        assertEquals("nyihaha", farm.getAnimalVoices().get(1));
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
        assertEquals(3, farm.getAnimalVoices().size());
        assertEquals("brekeke", farm.getAnimalVoices().get(0));
        assertEquals("kukuriku", farm.getAnimalVoices().get(1));
        assertEquals("nyihaha", farm.getAnimalVoices().get(2));
    }
}