package singleton;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrainerServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void clearRepository() {
        TrainerRepository.getInstance().clear();
    }

    @Test
    public void createAndDoNotFind() {
        expectedException.expect(IllegalArgumentException.class);

        TrainerService.getInstance().createTrainer("John Doe");
        TrainerService.getInstance().findByName("Jane");
    }

    @Test
    public void createAndFind() {
        TrainerService.getInstance().createTrainer("John Doe");
        Trainer trainer = TrainerService.getInstance().findByName("John Doe");
        assertThat(trainer.getName(), equalTo("John Doe"));
    }
}
