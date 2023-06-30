package classloader;

import java.io.IOException;
import java.io.InputStream;

public class VersionedClassLoader extends ClassLoader {

    private int version;

    public VersionedClassLoader(ClassLoader parent, int version) {
        super(parent);
        this.version = version;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("classloader.SimpleHelloService".equals(name)) {
            try {
                InputStream is = getClass().getResourceAsStream("SimpleHelloServiceV" + version + ".class");
                byte[] data = is.readAllBytes();
                return defineClass(null, data, 0, data.length);

            } catch (IOException ioe) {
                throw new IllegalStateException("Can not load class", ioe);
            }
        }

        return super.loadClass(name);
    }
}
