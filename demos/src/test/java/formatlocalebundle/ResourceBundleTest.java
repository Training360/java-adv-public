package formatlocalebundle;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceBundleTest {
    @Test
    public void testGetMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("hu", "HU"));
        String message = bundle.getString("label.save");
        assertEquals("Mentés", message);

        ResourceBundle enBundle = ResourceBundle.getBundle("messages", Locale.UK);
        String enMessage = enBundle.getString("label.save");
        assertEquals("Save", enMessage);
    }

}
