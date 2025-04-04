package ioreader.grades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchoolRecordsManagerTest {

    SchoolRecordsManager schoolRecordsManager = new SchoolRecordsManager();

    @Test
    void readFromFile() {
        schoolRecordsManager.readGradesFromFile("grades.txt");

        assertEquals(4, schoolRecordsManager.getStudents().size());
        assertEquals(3, schoolRecordsManager.getStudents().get(2).getGradeList().get(1).longValue());
        assertEquals("Jason_Butler", schoolRecordsManager.getStudents().get(3).getName());
        assertEquals(4.0, schoolRecordsManager.getStudents().get(2).average(), 0.000001);

    }

    @Test
    void classAverage() {
        schoolRecordsManager.readGradesFromFile("grades.txt");

        assertEquals(3.542, schoolRecordsManager.classAverage(), 0.001);
    }


}
