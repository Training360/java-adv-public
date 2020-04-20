package templatemethod;

public class ByNameCriteria implements SearchCriteria {

    private String name;

    public ByNameCriteria(String name) {
        this.name = name;
    }

    @Override
    public boolean accept(Trainer trainer) {
        return trainer.getName().equals(name);
    }
}
