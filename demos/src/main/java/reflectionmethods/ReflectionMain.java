package reflectionmethods;

import java.lang.reflect.Method;

public class ReflectionMain {

    public static void main(String[] args) {
        Trainer trainer = new Trainer("John Doe", 1970);

        try {
            Method method = Trainer.class.getMethod("setName", String.class);
            method.invoke(trainer, "Jack Doe");

            System.out.println(method.getParameters()[0].getName());

            Method getter = Trainer.class.getMethod("getName");
            String name = (String) getter.invoke(trainer);
            System.out.println(name);
        } catch (Exception e) {
            throw new IllegalStateException("Can not get method", e);
        }

        System.out.println(trainer.getName());
    }
}
