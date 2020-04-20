package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterCounter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterCounter.class);

    public int countCharacters(String source, String chars) {
        LOGGER.info("Finding '" + chars + "' characters in '" + source + "'");
        int counter = 0;
        for (int i = 0; i < source.length(); i++) {
            if (chars.indexOf(source.charAt(i)) >= 0) {
                LOGGER.info("'" + source.charAt(i) + "' character found at " + i + ". index");
                counter++;
            }
        }
        return counter;
    }
}
