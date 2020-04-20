package simplefactory;

public class StudentParser implements Parser {

    @Override
    public Student parse(String text) {
        String[] fields = text.split(",");
        return new Student(fields[1]);
    }
}
