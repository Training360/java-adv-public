package templatemethod;

public class ElderThanCriteria implements SearchCriteria {

    private int minAge;

    public ElderThanCriteria(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean accept(Trainer trainer) {
        return trainer.getAge() >= minAge;
    }
}
