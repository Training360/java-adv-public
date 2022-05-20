package singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainerServiceTest {

    @Test
    public void createAndFind() {
        TrainerRepository.getInstance().clear();

        TrainerService.getInstance().createTrainer("John Doe");
        Trainer trainer = TrainerService.getInstance().findByName("John Doe");
        assertEquals("John Doe", trainer.getName());
    }

    @Test
    public void createAndDoNotFind() {
        TrainerRepository.getInstance().clear();

        TrainerService.getInstance().createTrainer("John Doe");

        assertThrows(IllegalArgumentException.class, () -> {
            TrainerService.getInstance().findByName("Jane");
        });
    }
}
