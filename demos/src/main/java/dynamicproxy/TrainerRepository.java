package dynamicproxy;

import java.util.List;

public interface TrainerRepository {

    void addTrainer(String name);

    List<String> listTrainers();
}
