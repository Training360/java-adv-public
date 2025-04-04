package iowriter.music;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BandManagerTest {

   @TempDir
    File temporaryFolder;

    BandManager bandManager = new BandManager();

    @Test
    void createOlderThanFile() throws IOException {
        Path inputFile = new File(temporaryFolder,"test.txt").toPath();
        Files.copy(BandManagerTest.class.getResourceAsStream("bands_and_years.txt"), inputFile, StandardCopyOption.REPLACE_EXISTING);
        bandManager.readBandsFromFile(inputFile);

        Path outputFile =new File(temporaryFolder,"out.txt").toPath();
        bandManager.writeBandsBefore(outputFile, 1990);

        List<String> lines = Files.readAllLines(outputFile);

        assertEquals(5, lines.size());
    }

}
