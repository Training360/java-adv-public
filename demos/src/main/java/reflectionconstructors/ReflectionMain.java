package reflectionconstructors;

import java.lang.reflect.Constructor;

public class ReflectionMain {

    public static void main(String[] args) {
        try {
            Constructor constructor = Trainer.class.getConstructor(String.class, int.class);
            Trainer trainer = (Trainer) constructor.newInstance("John Doe", 1970);
            System.out.println(trainer.getName() + " - " + trainer.getYearOfBirth());
        } catch (Exception e) {
            throw new IllegalStateException("Can not get const", e);
        }
    }
}
