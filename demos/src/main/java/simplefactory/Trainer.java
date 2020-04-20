package simplefactory;

import java.util.List;

public class Trainer extends Human {

    private List<String> courses;

    public Trainer(String name, List<String> courses) {
        super(name);
        this.courses = courses;
    }

    public List<String> getCourses() {
        return courses;
    }
}
