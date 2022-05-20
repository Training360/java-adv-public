package formatlocaleregexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpValidator {

    public static final String EMAIL_PATTERN = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
    public static final String ACADEMIC_YEAR_PATTERN = "([2][0][0-9][0-9])\\-([2][0][0-9][0-9])";
    public static final String YEAR_PATTERN = "[1-2][0,9][0-9]{2}";

    public boolean validateEmail(String email) {
        if (isEmpty(email)) {
            throw new IllegalArgumentException("Empty string passed!");
        }
        return email.matches(EMAIL_PATTERN);
    }

    public boolean validateAcademicYear(String academicYear) {
        if (isEmpty(academicYear)) {
            throw new IllegalArgumentException("Empty string passed!");
        }
        return academicYear.matches(ACADEMIC_YEAR_PATTERN);
    }

    public boolean validateYear(String yearString) {
        if (isEmpty(yearString)) {
            throw new IllegalArgumentException("Empty string passed!");
        }
        Pattern pt = Pattern.compile(YEAR_PATTERN);
        Matcher m = pt.matcher(yearString);

        return m.matches();
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
