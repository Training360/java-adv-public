package reflectionattributes;

import java.lang.reflect.Field;

public class ReflectionMain {

    public static void main(String[] args) {
        Trainer trainer = new Trainer(null, 0);

        try {
            Field[] fields = Trainer.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType().isAssignableFrom(String.class)) {
                    field.setAccessible(true);
                    System.out.println(field.getName());
                    String name = (String) field.get(trainer);
                    System.out.println(name);
                    if (name == null) {
                        field.set(trainer, "!!!");
                    }
                }
            }
        } catch (IllegalAccessException i) {
            throw new IllegalStateException("Can not read", i);
        }

        System.out.println(trainer.getName());
    }
}
