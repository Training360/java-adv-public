package formatlocaleregexp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexpTest {

    @Test
    public void testPatternMatcher() {
       Pattern pattern = Pattern.compile("J([a-z]+)a");
       Matcher matcher = pattern.matcher("Jakara");
       assertTrue(matcher.matches());

       Matcher matcher1 = pattern.matcher("Java and Jakarta");
        List<String> s = new ArrayList<>();
       while(matcher1.find()) {
           s.add(matcher1.group(1));
       }
       assertEquals(Arrays.asList("av", "akart"), s);

       assertTrue("Java".matches("J[a-z]+a"));

        assertEquals("xxx and xxx", "Java and Jakarta".replaceAll("J[a-z]+a", "xxx"));
    }
}
