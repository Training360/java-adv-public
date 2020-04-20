package names;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NameWriter {

    private List<String> names = new ArrayList<>();
    private Path file;

    public NameWriter(String fileName) {
        this.file = Path.of("src/main/resources/"+fileName);
    }

    public void addAndWrite(String name) {
     names.add(name);
      try(BufferedWriter bw = Files.newBufferedWriter(file, StandardOpenOption.APPEND)){
          bw.write(name+"\n");
      } catch (IOException e) {
          throw new IllegalStateException("Can't open file!",e);
      }
    }


    public List<String> getNames() {
        return new ArrayList<>(names);
    }

}
