package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AuthorizeHandler implements InvocationHandler {

    private Object target;

    public AuthorizeHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!isAllowed(target, (String) args[0])) {
            throw new SecurityException("Method call is denied");
        }
        return method.invoke(target, args);
    }

    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AuthorizeHandler(target));
    }

    private boolean isAllowed(Object target, String user) {
        if (target instanceof Door && user.equals("John Doe")) {
            return true;
        }
        if (target instanceof Printer && user.equals("Jane Doe")) {
            return true;
        }
        return false;
    }
}
