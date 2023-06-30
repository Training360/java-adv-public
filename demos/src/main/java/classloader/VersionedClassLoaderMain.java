package classloader;

public class VersionedClassLoaderMain {

    public static void main(String[] args) throws Exception {
        VersionedClassLoader cl1 = new VersionedClassLoader(ClassLoaderMain.class.getClassLoader(), 1);
        HelloService s = (HelloService) cl1.loadClass("classloader.SimpleHelloService").getDeclaredConstructor().newInstance();
        System.out.println(s.sayHello());
        VersionedClassLoader cl2 = new VersionedClassLoader(ClassLoaderMain.class.getClassLoader(), 2);
        s = (HelloService) cl2.loadClass("classloader.SimpleHelloService").getDeclaredConstructor().newInstance();
        System.out.println(s.sayHello());
    }
}
