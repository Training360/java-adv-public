package classloader;

public class ClassLoaderMain {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(java.sql.Date.class.getClassLoader());
        System.out.println(ClassLoaderMain.class.getClassLoader());
    }
}
