package templatemethod;

import java.util.ArrayList;
import java.util.List;

public class TrainerRepository {

    private List<Trainer> trainers = new ArrayList<>();

    public TrainerRepository(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Trainer> findByCriteria(SearchCriteria searchCriteria) {
        List<Trainer> result = new ArrayList<>();
        for (Trainer trainer: trainers) {
            if (searchCriteria.accept(trainer)) {
                result.add(trainer);
            }
        }
        return result;
    }


}
