package logging;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterCounterTest {

    @Test
    public void testFind() {
        assertEquals(0, new CharacterCounter().countCharacters("", "abcd"));
        assertEquals(0, new CharacterCounter().countCharacters("abcd", ""));
        assertEquals(1, new CharacterCounter().countCharacters("a", "a"));
        assertEquals(4, new CharacterCounter().countCharacters("aaaa", "a"));
        assertEquals(4, new CharacterCounter().countCharacters("aaaa", "abcd"));
        assertEquals(5, new CharacterCounter().countCharacters("abcdabcdabcdabce", "ae"));
    }
}
