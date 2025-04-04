package iowritestring.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTest {

    @TempDir
    public Path temporaryFolder;

    Register register = new Register();

    @Test
    void testRegisterStudent() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("kiss_luca.txt"));
        register.registerStudent("Kiss Luca", file);

        assertTrue(Files.exists(temporaryFolder.resolve("kiss_luca.txt")));

        String name = Files.readString(file);

        assertEquals("Kiss Luca" + "\n", name);
    }

    @Test
    public void testNewMarkFirstMark() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("kiss_luca.txt"));
        register.registerStudent("Kiss Luca", file);
        register.registerNewMark(file, 5);
        List<String> testList = Files.readAllLines(file);

        assertEquals(2, testList.size());
        assertEquals("Kiss Luca", testList.get(0));
        assertEquals("5", testList.get(1));
    }

    @Test
    public void testNewMark() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("kiss_luca.txt"));
        register.registerStudent("Kiss Luca", file);
        register.registerNewMark(file, 5);
        register.registerNewMark(file, 3);
        register.registerNewMark(file, 2);
        register.registerNewMark(file, 2);
        List<String> testList = Files.readAllLines(file);

        assertEquals(5, testList.size());
        assertEquals("2", testList.get(3));
    }

    @Test
    public void averageTest() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("kiss_luca.txt"));
        register.registerStudent("Kiss Luca", file);
        register.registerNewMark(file, 5);
        register.registerNewMark(file, 3);
        register.registerNewMark(file, 2);
        register.registerNewMark(file, 2);
        register.writeAverage(file);
        List<String> testList = Files.readAllLines(file);

        assertEquals(6, testList.size());
        assertEquals("average: 3.0", testList.get(5));
    }
}
