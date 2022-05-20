package annotationsrepeating;

public class NameValidator implements Validator {

    @Override
    public boolean isValid(Object value) {
        String name = (String) value;
        return !(name == null || name.isBlank());
    }
}
