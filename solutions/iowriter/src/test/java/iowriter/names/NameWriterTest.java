package iowriter.names;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameWriterTest {

    @TempDir
    Path temporaryFolder;

    NameWriter nameWriter;

    @Test
    void write() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("test.txt"));
        System.out.println(file);
        nameWriter = new NameWriter(file);
        nameWriter.addAndWrite("John Smith");

        List<String> actual = Files.readAllLines(file);

        assertEquals(List.of("John Smith"), actual);
    }

    @Test
    void append() throws IOException {
        Path file = Files.createFile(temporaryFolder.resolve("test.txt"));

        nameWriter = new NameWriter(file);
        nameWriter.addAndWrite("John Smith");
        nameWriter.addAndWrite("John Doe");

        List<String> actual = Files.readAllLines(file);

        assertEquals(List.of("John Smith", "John Doe"), actual);
    }


}
