package singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainerRepository {

    private static final TrainerRepository INSTANCE = new TrainerRepository();

    //    private List<Trainer> trainers = (List<Trainer>) Collections.synchronizedList(new ArrayList<Trainer>());
    private List<Trainer> trainers = new ArrayList<Trainer>();

    private TrainerRepository() {
    }

    public void save(Trainer trainer) {
        trainers.add(trainer);
    }

    public Trainer findByName(String name) {
        for (Trainer trainer : trainers) {
            if (trainer.getName().equals(name)) {
                return trainer;
            }
        }
        throw new IllegalArgumentException("Trainer not found with name: " + name);
    }

    public static TrainerRepository getInstance() {
        return INSTANCE;
    }

    public void clear() {
        trainers.clear();
    }
}
