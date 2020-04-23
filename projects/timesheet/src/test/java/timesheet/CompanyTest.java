package timesheet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompanyTest {

    Company company = new Company(new Validator());

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void readEmployees() {
        assertThat(company.getEmployees().get(0).getName(), is("John Connor"));
        assertThat(company.getEmployees().get(3).getName(), is("Vincent Vega"));
    }

    @Test
    public void readProjects() {
        assertThat(company.getProjects().get(0).getName(), is("Java"));
        assertThat(company.getProjects().get(3).getName(), is("C++"));
    }

    @Test
    public void emptyReportListWithProjectsTest() {
        assertThat(company.emptyReportListWithProjects().get(0).getProject().getName(), is("Java"));
        assertThat(company.emptyReportListWithProjects().get(3).getTime(), is(0L));
    }

    @Test
    public void sumHoursTest() {
        List<ReportLine> reportLines = Arrays.asList(new ReportLine(new Project("Java"), 12), new ReportLine(new Project("C++"), 3));

        assertThat(company.sumHours(reportLines), is(15L));

    }

    @Test
    public void calculateProjectByNameYearMonthTest() {
        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 27, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 27, 16, 10, 0)
        );


        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 26, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 26, 10, 10, 0)
        );


        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("C++"),
                LocalDateTime.of(2013, Month.JANUARY, 25, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 25, 12, 10, 0)
        );
        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("C++"),
                LocalDateTime.of(2013, Month.FEBRUARY, 25, 8, 0, 0),
                LocalDateTime.of(2013, Month.FEBRUARY, 25, 12, 10, 0)
        );


        assertThat(company.calculateProjectByNameYearMonth("John Connor", 2013, Month.JANUARY).get(0).getTime(), is(10L));
        assertThat(company.calculateProjectByNameYearMonth("John Connor", 2013, Month.JANUARY).get(1).getTime(), is(0L));
        assertThat(company.calculateProjectByNameYearMonth("John Connor", 2013, Month.JANUARY).get(3).getTime(), is(4L));
    }


    @Test
    public void wrongNameTest() throws IOException {
        expectedException.expect(IllegalArgumentException.class);

        company.prepareReport("James", 2013, Month.JANUARY);
    }

    @Test
    public void prepareAndPrintReportTest() throws IOException {
        //Given
        File report = temporaryFolder.newFile();
        String expected = "John Connor\t2013-01-01\t14\n" +
                "Java\t10\n" +
                "C++\t4";

        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 27, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 27, 16, 10, 0)
        );
        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("Java"),
                LocalDateTime.of(2013, Month.JANUARY, 26, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 26, 10, 10, 0)
        );
        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("C++"),
                LocalDateTime.of(2013, Month.JANUARY, 25, 8, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 25, 12, 10, 0)
        );
        company.addTimeSheetItem(
                new Employee("John", "Connor"),
                new Project("C++"),
                LocalDateTime.of(2013, Month.FEBRUARY, 25, 8, 0, 0),
                LocalDateTime.of(2013, Month.FEBRUARY, 25, 12, 10, 0)
        );

        //When
        String content = company.prepareReport("John Connor", 2013, Month.JANUARY);
        company.printToFile("John Connor", 2013, Month.JANUARY, report.toPath());

        //Then
        assertThat(content, equalTo(expected));
        assertThat((long) content.getBytes().length, equalTo(report.length()));
    }
}