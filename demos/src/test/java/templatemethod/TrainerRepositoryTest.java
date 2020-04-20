package templatemethod;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrainerRepositoryTest {

    private List<Trainer> trainers = Arrays.asList(new Trainer("John Doe", 30),
            new Trainer("Jane Doe", 28),
            new Trainer("John Roe", 18),
            new Trainer("John Smith", 40));

    @Test
    public void testSearchByName() {
        List<Trainer> result = new TrainerRepository(trainers).findByCriteria(new ByNameCriteria("Jane Doe"));
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getName(), equalTo("Jane Doe"));

        result = new TrainerRepository(trainers).findByCriteria(new ByNameCriteria("Nobody"));
        assertThat(result.size(), equalTo(0));
    }

    @Test
    public void testSearchByAge() {
        List<Trainer> result = new TrainerRepository(trainers).findByCriteria(new ElderThanCriteria(20));
        assertThat(result.size(), equalTo(3));

    }
}
