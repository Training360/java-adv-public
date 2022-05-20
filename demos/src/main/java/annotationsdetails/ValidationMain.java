package annotationsdetails;

import java.lang.reflect.Field;

public class ValidationMain {

    public static void main(String[] args) {
        Trainer trainer = new Trainer("" , 1970);

        for (Field field: Trainer.class.getDeclaredFields()) {
            ValidatedField validatedField = field.getAnnotation(ValidatedField.class);
            if (validatedField != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(trainer);
                    Class<? extends Validator>[] validators = validatedField.validators();
                    for (Class<? extends Validator> validatorClass: validators) {
                        System.out.println(validatorClass);
                        System.out.println(value);
                        Validator validator = validatorClass.getConstructor().newInstance();
                        if (!validator.isValid(value)) {
                            throw new IllegalArgumentException(field.getName() + " " +
                                    validatedField.value() + " " + value);
                        }
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("Can not read value", e);
                }
            }
        }
    }
}
