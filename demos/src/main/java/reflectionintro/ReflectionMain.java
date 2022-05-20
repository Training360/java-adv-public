package reflectionintro;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {

    public static void main(String[] args) {
        Class<Trainer> trainerClass = Trainer.class;
        Method[] methods = trainerClass.getDeclaredMethods();
        for (Method method: methods) {
            if (method.getName().startsWith("get")) {
                System.out.println(method.getName().substring(3));
            }
        }
        Field[] fields = trainerClass.getDeclaredFields();
        for (Field field: fields) {
            System.out.println(field.getName());
        }
        for (Constructor constructor: trainerClass.getConstructors()) {
            System.out.println(constructor.getName());
        }
    }
}
