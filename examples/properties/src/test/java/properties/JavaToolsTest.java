package properties;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaToolsTest {

    JavaTools javaTools = new JavaTools();

    @Test
    void testGetToolKeys() {
        assertEquals(new HashSet<>(Arrays.asList("maven", "junit", "jdk")), javaTools.getToolKeys());
    }

    @Test
    void testGetTools() {
        assertEquals(new HashSet<>(Arrays.asList("Apache Maven", "Java Development Kit", "JUnit")), javaTools.getTools());
    }

    @Test
    void testGetName() {
        assertEquals("JUnit", javaTools.getName("junit"));
        assertEquals("Java Development Kit", javaTools.getName("jdk"));
    }

    @Test
    void testGetUrl() {
        assertEquals("http://junit.org/junit4/", javaTools.getUrl("junit"));
        assertEquals("http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html", javaTools.getUrl("jdk"));
    }
}