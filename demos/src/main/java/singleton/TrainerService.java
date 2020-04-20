package singleton;

public class TrainerService {

    private static final TrainerService INSTANCE = new TrainerService();

    private TrainerService() {
    }

    public void createTrainer(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Empty name");
        }
        TrainerRepository.getInstance().save(new Trainer(name));
    }

    public Trainer findByName(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("Empty name");
        }
        return TrainerRepository.getInstance().findByName(name);
    }

    public static TrainerService getInstance() {
        return INSTANCE;
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
