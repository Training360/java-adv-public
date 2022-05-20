package genericclass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {

    @Test
    public void testCreate() {
        Container<Human> c = new Container<>(new Human("John"));
        assertEquals(Human.class, c.getValue().getClass());
        assertEquals("John", c.getName());

        Container<Subject> c2 = new Container<>(new Subject("Java"));
        assertEquals(Subject.class, c2.getValue().getClass());
        assertEquals("Java", c2.getName());


    }

}
