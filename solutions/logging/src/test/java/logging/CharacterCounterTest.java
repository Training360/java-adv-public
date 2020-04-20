package logging;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterCounterTest {

    @Test
    public void testFind() {
        assertThat(new CharacterCounter().countCharacters("", "abcd"),
                equalTo(0));
        assertThat(new CharacterCounter().countCharacters("abcd", ""),
                equalTo(0));
        assertThat(new CharacterCounter().countCharacters("a", "a"),
                equalTo(1));
        assertThat(new CharacterCounter().countCharacters("aaaa", "a"),
                equalTo(4));
        assertThat(new CharacterCounter().countCharacters("aaaa", "abcd"),
                equalTo(4));
        assertThat(new CharacterCounter().countCharacters("abcdabcdabcdabce", "ae"),
            equalTo(5));
    }
}
