package annotationsdetails;

public class NotNullValidator implements Validator {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
