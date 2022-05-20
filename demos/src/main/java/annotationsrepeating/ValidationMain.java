package annotationsrepeating;

import java.lang.reflect.Field;

public class ValidationMain {

    public static void main(String[] args) {
        Trainer trainer = new Trainer("   " , 1970);

        for (Field field: Trainer.class.getDeclaredFields()) {
            ValidatedFields validatedFields = field.getAnnotation(ValidatedFields.class);
            if (validatedFields != null) {
                for (ValidatedField validatedField : validatedFields.value()) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(trainer);
                        Class<? extends Validator> validatorClass = validatedField.value();
                        Validator validator = validatorClass.getConstructor().newInstance();
                        if (!validator.isValid(value)) {
                            throw new IllegalArgumentException("Validation failed " +
                                    field.getName() + " " + validatorClass);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException("Can not read value", e);
                    }
                }
            }
        }
    }
}
