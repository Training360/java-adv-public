package reflectionclasses;

import java.lang.reflect.Modifier;
import java.util.Map;

public class ReflectionMain {

    public static void main(String[] args) {
        Class[] classes = Trainer.class.getDeclaredClasses();
        for (Class aClass: classes) {
            System.out.println(aClass.getName());
        }

        int modifiers = Trainer.class.getModifiers();
        System.out.println(modifiers);
        System.out.println(Modifier.toString(modifiers));
        System.out.println(Modifier.isPublic(modifiers));

        Trainer trainer = new Trainer("John Doe", 1970);

        System.out.println(Trainer.class.isAssignableFrom(trainer.getClass()));

        System.out.println(Trainer.class.isInstance(trainer));

        Package[] packages = Package.getPackages();
        for (Package aPackage: packages) {
            System.out.println(aPackage.toString());
        }
    }
}
