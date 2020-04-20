package dpintro.iteratorpattern;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class IteratorTest {

    private List<Animal> animalList = Arrays.asList(new Animal("zsiráf"), new Animal("oroszlán"), new Animal("gazella"), new Animal("leopárd"));
    private List<String> testList = Arrays.asList("leopárd", "gazella", "oroszlán", "zsiráf");

    @Test
    public void testIterator() {
        //Given
        Zoo zoo = new Zoo(animalList);
        Iterator iter = zoo.getIterator();
        //Then
        while (iter.hasNext()) {
            assertThat(testList, hasItem(((Animal) iter.next()).getName()));
        }
    }
}
