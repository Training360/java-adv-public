package timesheet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProjectTest {

    Project project = new Project("Java");

    @Test
    public void createProjectTest() {
        assertThat(project.getName(), is("Java"));
    }
}