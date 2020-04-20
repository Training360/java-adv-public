package names;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NameWriterTest {


    private NameWriter nameWriter = new NameWriter("names.txt");
    private Path file = Path.of("src/main/resources/names.txt");

    @Before
    public void makeFileEmpty(){
        try(BufferedWriter bw = Files.newBufferedWriter(file)){

            bw.write("");

        } catch (IOException e) {
            throw new IllegalStateException("Can't open file!",e);
        }
    }


    @Test
    public void addAndWriteTest() throws IOException {
        nameWriter.addAndWrite("John Smith");
        nameWriter.addAndWrite("John Doe");

        List<String> test= Files.readAllLines(file);

        assertEquals(2,test.size());
        assertEquals(2,nameWriter.getNames().size());
        assertEquals(test.get(0),nameWriter.getNames().get(0));

    }



}
