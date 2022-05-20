package reflectiongenerics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionMain {

    public static void main(String[] args) {
        try {
            Field field = Trainer.class.getDeclaredField("skills");
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type typeItem: types) {
                    System.out.println(typeItem.getTypeName());
                }
            }
        }
        catch (NoSuchFieldException e) {
            throw new IllegalStateException("Can not read field", e);
        }

        try {
            Method method = Trainer.class.getMethod("getSkills");
            Type type = method.getGenericReturnType();
            System.out.println(((ParameterizedType) type).getActualTypeArguments()[0].getTypeName());
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Can not read method", e);
        }
    }
}
