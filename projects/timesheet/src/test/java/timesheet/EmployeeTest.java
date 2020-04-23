package timesheet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class EmployeeTest {

    Employee employee = new Employee("John", "Connor");

    @Test
    public void createEmployee() {
        assertThat(employee.getName(), is("John Connor"));
    }
}