package timesheet;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidatorsTest {

    private Validator validator = new Validator();

    @Test
    public void notSameDayTest() {
        assertThat(validator.notSameDay(LocalDateTime.of(2013, Month.JANUARY, 27, 16, 1, 1),
                LocalDateTime.of(2014, Month.JANUARY, 28, 17, 1, 6)), is(true));
        assertThat(validator.notSameDay(LocalDateTime.of(2014, Month.JANUARY, 27, 16, 1, 1),
                LocalDateTime.of(2014, Month.JANUARY, 28, 17, 1, 6)), is(true));
        assertThat(validator.notSameDay(LocalDateTime.of(2014, Month.JANUARY, 27, 16, 1, 1),
                LocalDateTime.of(2014, Month.JANUARY, 27, 17, 1, 6)), is(false));
    }

    @Test
    public void beginDateLaterTest() {
        assertThat(validator.beginDateIsLater(LocalDateTime.of(2014, Month.JANUARY, 28, 16, 1, 1),
                LocalDateTime.of(2014, Month.JANUARY, 28, 15, 1, 6)), is(true));
        assertThat(validator.beginDateIsLater(LocalDateTime.of(2014, Month.JANUARY, 28, 16, 1, 1),
                LocalDateTime.of(2014, Month.JANUARY, 28, 17, 1, 6)), is(false));
    }

    @Test
    public void notInEmployeesListTest() {
        List<Employee> employees = Arrays.asList(new Employee("John", "Connor"), new Employee("Vincent", "Vega"));

        assertThat(validator.notInEmployeesList(employees, "Steve Harris"), is(true));
        assertThat(validator.notInEmployeesList(employees, "John Connor"), is(false));
    }
}