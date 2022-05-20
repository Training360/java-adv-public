package solid.base;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testCreation() {
        final String expectedLoginName = "noname";

        User user = new User(expectedLoginName);

        assertEquals(expectedLoginName, user.getLoginName());
    }
}