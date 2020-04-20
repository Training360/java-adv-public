package salary;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SalaryWriterTest {

    private SalaryWriter sw = new SalaryWriter(List.of("Dr. John Doe", "Mrs. Jane Doe", "Dr. Julie Smith", "Jack Doe"));


    @Before
    public void deleteFile() throws IOException {
        Path file = Path.of("src/main/resources/testFile.txt");
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }


    @Test
    public void testWriteNamesAndSalaries() throws IOException {
        sw.writeNamesAndSalaries("testFile.txt");
        Path file = Path.of("src/main/resources/testFile.txt");
        List<String> writtenLines = Files.readAllLines(file);


        assertEquals(true, writtenLines.get(2).equals("Dr. Julie Smith: 500000"));
        assertEquals(true, writtenLines.get(1).equals("Mrs. Jane Doe: 200000"));
        assertEquals(true, writtenLines.get(3).equals("Jack Doe: 100000"));


    }

}
