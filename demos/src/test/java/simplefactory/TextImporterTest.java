package simplefactory;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextImporterTest {

    @Test
    public void testImportStudents() {
        String line = "s,John Doe\ns,Jane Doe";
        List<Human> humans = new TextImporter().importText(line);
        assertTrue(humans.get(0) instanceof Student);
        assertEquals("John Doe", (humans.get(0)).getName());
        assertTrue(humans.get(1) instanceof Student);
        assertEquals("Jane Doe", (humans.get(1)).getName());
    }

    @Test
    public void testImportTrainers() {
        String line = "t,John Doe,Math,Biology\nt,Jane Doe,Physics";
        List<Human> humans = new TextImporter().importText(line);
        assertTrue(humans.get(0) instanceof Trainer);
        assertEquals("John Doe", (humans.get(0)).getName());
        assertEquals(Arrays.asList("Math", "Biology"), ((Trainer) humans.get(0)).getCourses());
        assertTrue(humans.get(1) instanceof Trainer);
        assertEquals("Jane Doe", (humans.get(1)).getName());
        assertEquals(Arrays.asList("Physics"), ((Trainer) humans.get(1)).getCourses());
    }

    @Test
    public void testImportMixed() {
        String line = "s,John Doe\nt,Jane Doe,Math,Biology";
        List<Human> humans = new TextImporter().importText(line);
        assertTrue(humans.get(0) instanceof Student);
        assertEquals("John Doe", (humans.get(0)).getName());
        assertTrue(humans.get(1) instanceof Trainer);
        assertEquals("Jane Doe", (humans.get(1)).getName());
        assertEquals(Arrays.asList("Math", "Biology"), ((Trainer) humans.get(1)).getCourses());
    }
}
