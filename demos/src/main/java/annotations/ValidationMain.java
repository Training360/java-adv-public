package annotations;

import java.lang.reflect.Field;

public class ValidationMain {

    public static void main(String[] args) {
        Trainer trainer = new Trainer(null, 1970);

        for (Field field: Trainer.class.getDeclaredFields()) {
            NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
            if (notNullAnnotation != null) {
                System.out.println(field.getName());
                field.setAccessible(true);
                try {
                    Object value = field.get(trainer);
                    if (value == null) {
                        throw new IllegalArgumentException(field.getName() + "should not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Can not get value", e);
                }
            }
        }
    }
}
