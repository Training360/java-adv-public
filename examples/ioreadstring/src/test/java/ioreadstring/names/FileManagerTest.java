package ioreadstring.names;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileManagerTest {

    private FileManager fm = new FileManager("src/test/resources/names.txt");

    @Test
    void createClass() {
        assertEquals("names.txt", fm.getMyFile().getFileName().toString());
        assertEquals(0, fm.getHumans().size());
    }


    @Test
    void readFromFile() {
        fm.readFromFile();
        assertEquals(5, fm.getHumans().size());
        assertEquals("Jane", fm.getHumans().get(1).getFirstName());
    }

}
