package dynamicproxy;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTrainerRepository implements TrainerRepository {

    private List<String> trainers = new ArrayList<>();

    @Override
    public void addTrainer(String name) {
        trainers.add(name);
    }

    @Override
    public List<String> listTrainers() {
        return trainers;
    }
}
