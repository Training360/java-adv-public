package genericclass;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameConcatenatorTest {

    @Test
    public void testConcat() {
        List<Human> humans = Arrays.asList(new Human("John"), new Human("Jack"), new Human("Jane"));
        String s = new NameConcatenator().concat(humans);
        assertEquals("John, Jack, Jane, ", s);
    }

    @Test
    public void testAdd() {
        List<Human> l = new ArrayList<>();
        new NameConcatenator().add(l);
        assertEquals("Jack", l.get(0).getName());

        List<Trainer> l2 = new ArrayList<>();
        new NameConcatenator().add(l2);
        assertEquals("Jack", l2.get(0).getName());
    }
}
