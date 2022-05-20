package reflectionforname;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ReflectionMain {

    public static void main(String[] args) {
        List<String> desc = List.of("training.Course,Java",
                "training.Trainer,John Doe",
                "training.Trainer,Jack Doe",
                "training.Course,Python");
        List<Object> items = new ArrayList<>();
        for (String descItem: desc) {
            String[] fields = descItem.split(",");
            String className = fields[0];
            String name = fields[1];

            try {
                Class<?> aClass = Class.forName(className);
                Constructor<?> constructor = aClass.getConstructor(String.class);
                Object item = constructor.newInstance(name);
                items.add(item);
            } catch (Exception e) {
                throw new IllegalStateException("Can not inst.", e);
            }
        }
        System.out.println(items);
    }
}
