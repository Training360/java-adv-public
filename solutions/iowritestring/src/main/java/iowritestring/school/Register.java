package iowritestring.school;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Register {

    public void registerStudent(String name, Path file) {
        try {
            Files.writeString(file, name + "\n");
        } catch (IOException e) {
            throw new IllegalStateException("Can't create file", e);
        }
    }

    public void registerNewMark(Path file, int mark) {
        try {
            Files.writeString(file, mark + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Can't open file", e);
        }
    }

    public void writeAverage(Path file) {
        try {
            List<String> myFile = Files.readAllLines(file);
            double sum = 0;
            for (int i = 1; i < myFile.size(); i++) {
                sum += Double.parseDouble(myFile.get(i));
            }

            Files.writeString(file, "average: " + (sum / (myFile.size() - 1)), StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new IllegalStateException("Can't open file", e);
        }
    }
}
