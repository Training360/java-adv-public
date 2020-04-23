package timesheet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TimeSheetItemTest {

    TimeSheetItem timeSheetItem = new TimeSheetItem(
            new Validator(),
            new Employee("John", "Connor"),
            new Project("Java"),
            LocalDateTime.of(2013, Month.JANUARY, 27, 8, 0, 0),
            LocalDateTime.of(2013, Month.JANUARY, 27, 16, 10, 0)
    );

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void notSameDateTest() {
        expectedException.expect(IllegalArgumentException.class);

        new TimeSheetItem(
                new Validator(),
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 26, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 27, 16, 10, 0)
        );
    }

    @Test
    public void beginDateLaterThanEndTest() {
        expectedException.expect(IllegalArgumentException.class);

        new TimeSheetItem(
                new Validator(),
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 27, 16, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 27, 8, 10, 0)
        );
    }


    @Test
    public void createTimeSheetItem() {

        assertThat(timeSheetItem.getEmployee().getName(), is("John Connor"));
        assertThat(timeSheetItem.getProject().getName(), is("Java"));
        assertThat(timeSheetItem.getBeginDate(), is(LocalDateTime.of(2013, Month.JANUARY, 27, 8, 0, 0)));
        assertThat(timeSheetItem.getEndDate(), is(LocalDateTime.of(2013, Month.JANUARY, 27, 16, 10, 0)));
    }

    @Test
    public void countDifferenceBetweenDatesTest() {

        assertThat(timeSheetItem.countDifferenceBetweenDates().toHours(), is(8L));
    }
}