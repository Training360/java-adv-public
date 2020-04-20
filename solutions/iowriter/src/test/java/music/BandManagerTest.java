package music;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BandManagerTest {
    private BandManager bandManager = new BandManager("bandsandyears.txt");


    @Before
    public void deleteCreatedFiles() throws IOException {
        Path deletFile = Path.of("src/main/resources/olderThan30.txt");
        Files.delete(deletFile);
    }

    @Test
    public void createOlderThanFile() throws IOException {
        bandManager.readBandsFromFile();
        bandManager.writeOlderBandsThanToFile(30);

        Path createdFile = Path.of("src/main/resources/olderThan30.txt");


        List<String> lines = Files.readAllLines(createdFile);

        assertEquals(5,lines.size());
        assertEquals(true,lines.get(0).startsWith("Metallica"));
        assertEquals(true,lines.get(3).startsWith("Edda"));


    }
}
