package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TrainerService {

    public static void main(String[] args) {
        TrainerRepository target = new InMemoryTrainerRepository();

        TrainerRepository trainerRepository = (TrainerRepository) Proxy.newProxyInstance(TrainerService.class.getClassLoader(),
                new Class[]{TrainerRepository.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + " " + Arrays.toString(args));
                        return method.invoke(target, args);
                    }
                });

        trainerRepository.addTrainer("John Doe");
        System.out.println(trainerRepository.listTrainers());
    }
}
