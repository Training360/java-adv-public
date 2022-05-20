package templatemethod;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerRepositoryTest {

    private List<Trainer> trainers = Arrays.asList(new Trainer("John Doe", 30),
            new Trainer("Jane Doe", 28),
            new Trainer("John Roe", 18),
            new Trainer("John Smith", 40));

    @Test
    public void testSearchByName() {
        List<Trainer> result = new TrainerRepository(trainers).findByCriteria(new ByNameCriteria("Jane Doe"));
        assertEquals(1, result.size());
        assertEquals("Jane Doe", result.get(0).getName());

        result = new TrainerRepository(trainers).findByCriteria(new ByNameCriteria("Nobody"));
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByAge() {
        List<Trainer> result = new TrainerRepository(trainers).findByCriteria(new ElderThanCriteria(20));
        assertEquals(3, result.size());

    }
}
