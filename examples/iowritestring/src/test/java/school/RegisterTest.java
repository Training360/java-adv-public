package school;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegisterTest {


    private Register register = new Register();


    @Before
    public void deleteTestFilesIfExists() throws IOException {
        Path file = Path.of("src/main/resources/john_doe.txt");
        Path file2 = Path.of("src/main/resources/julie_doe.txt");
        if (Files.exists(file)) {
            Files.delete(file);
        }
        if (Files.exists(file2)) {
            Files.delete(file2);
        }
    }


    @Test
    public void newMarkTestIfNotExists() throws IOException {

        register.newMark("john_doe", 5);
        Path testFile = Path.of("src/main/resources/john_doe.txt");
        List<String> testList = Files.readAllLines(testFile);

        assertEquals(1, testList.size());
        assertEquals("5", testList.get(0));


    }


    @Test
    public void newMarkTestIfExists() throws IOException {
        Path testFile = Path.of("src/main/resources/jane_doe.txt");
        List<String> testList = Files.readAllLines(testFile);
        int beforeSize = testList.size();

        register.newMark("jane_doe", 3);
        testList = Files.readAllLines(testFile);

        assertEquals(beforeSize + 1, testList.size());
    }

    @Test
    public void averageTest() throws IOException {
        Path testFile = Path.of("src/main/resources/julie_doe.txt");
        Files.writeString(testFile, "5\n");
        Files.writeString(testFile, "3\n", StandardOpenOption.APPEND);
        Files.writeString(testFile, "4\n", StandardOpenOption.APPEND);

        register.average("julie_doe");

        List<String> avgTestList = Files.readAllLines(testFile);
        assertEquals(4, avgTestList.size());
        assertEquals("average: 4.0", avgTestList.get(avgTestList.size() - 1));

    }


}
