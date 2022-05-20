package builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainerBuilderTest {

    @Test
    public void createWithoutName() {
        Trainer trainer = new TrainerBuilder().withAge(30).build();
        assertEquals("anonymous", trainer.getName());
        assertEquals(30, trainer.getAge());
    }

    @Test
    public void createWithName() {
        Trainer trainer = new TrainerBuilder().withName("John Doe").withAge(30).build();
        assertEquals("John Doe", trainer.getName());
        assertEquals(30, trainer.getAge());
    }

    @Test
    public void createWithoutAge() {
        assertThrows(IllegalStateException.class, () -> {
            new TrainerBuilder().build();
        });
    }

    @Test
    public void createWithInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainerBuilder().withAge(10);
        });
    }

    @Test
    public void createWithInvalidFormatAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TrainerBuilder().withAge("ten").build();
        });
    }
}
