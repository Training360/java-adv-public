package timesheet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportLineTest {

    ReportLine reportLine = new ReportLine(new Project("Java"), 10);

    @Test
    public void createReportLine() {
        assertThat(reportLine.getProject().getName(), is("Java"));
        assertThat(reportLine.getTime(), is(10L));
    }

    @Test
    public void setTimeTest() {
        reportLine.setTime(2);
        assertThat(reportLine.getTime(), is(12L));
    }
}