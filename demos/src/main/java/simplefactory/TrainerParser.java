package simplefactory;

import java.util.Arrays;

public class TrainerParser implements Parser {

    @Override
    public Trainer parse(String text) {
        String[] fields = text.split(",");
        return new Trainer(fields[1], Arrays.asList(fields).subList(2, fields.length));
    }
}
