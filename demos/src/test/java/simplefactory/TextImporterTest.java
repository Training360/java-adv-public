package simplefactory;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TextImporterTest {

    @Test
    public void testImportStudents() {
        String line = "s,John Doe\ns,Jane Doe";
        List<Human> humans = new TextImporter().importText(line);
        assertThat(humans.get(0), instanceOf(Student.class));
        assertThat((humans.get(0)).getName(), equalTo("John Doe"));
        assertThat(humans.get(1), instanceOf(Student.class));
        assertThat((humans.get(1)).getName(), equalTo("Jane Doe"));
    }

    @Test
    public void testImportTrainers() {
        String line = "t,John Doe,Math,Biology\nt,Jane Doe,Physics";
        List<Human> humans = new TextImporter().importText(line);
        assertThat(humans.get(0), instanceOf(Trainer.class));
        assertThat((humans.get(0)).getName(), equalTo("John Doe"));
        assertThat(((Trainer) humans.get(0)).getCourses(), equalTo(Arrays.asList("Math", "Biology")));
        assertThat(humans.get(1), instanceOf(Trainer.class));
        assertThat((humans.get(1)).getName(), equalTo("Jane Doe"));
        assertThat(((Trainer) humans.get(1)).getCourses(), equalTo(Arrays.asList("Physics")));
    }

    @Test
    public void testImportMixed() {
        String line = "s,John Doe\nt,Jane Doe,Math,Biology";
        List<Human> humans = new TextImporter().importText(line);
        assertThat(humans.get(0), instanceOf(Student.class));
        assertThat((humans.get(0)).getName(), equalTo("John Doe"));
        assertThat(humans.get(1), instanceOf(Trainer.class));
        assertThat((humans.get(1)).getName(), equalTo("Jane Doe"));
        assertThat(((Trainer) humans.get(1)).getCourses(), equalTo(Arrays.asList("Math", "Biology")));
    }
}
