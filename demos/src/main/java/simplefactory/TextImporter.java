package simplefactory;

import java.util.ArrayList;
import java.util.List;

public class TextImporter {

    public List<Human> importText(String text) {
        List<Human> humans = new ArrayList<>();
        String lines[] = text.split("\\r?\\n");
        for (String line: lines) {
            String fields[] = line.split(",");
            Parser parser = new ParserFactory().createParser(fields[0]);
            Human human = parser.parse(line);
            humans.add(human);
        }
        return humans;
    }
}
