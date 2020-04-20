package simplefactory;

public class ParserFactory {

    public Parser createParser(String type) {
        switch (type.toLowerCase()) {
            case "s":
                return new StudentParser();
            case "t":
                return new TrainerParser();
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }
}
