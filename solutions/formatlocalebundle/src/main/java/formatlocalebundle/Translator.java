package formatlocalebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {

    public String englishToHungarian(String key) {

        Locale locale = new Locale("hu");
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);

        if (!bundle.containsKey(key)) {
            throw new IllegalArgumentException("Key not found in bundle!");
        }
        return bundle.getString(key);
    }

    public String englishToGerman(String key) {

        ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.GERMAN);

        if (!bundle.containsKey(key)) {
            throw new IllegalArgumentException("Key not found in bundle!");
        }
        return bundle.getString(key);
    }
}
