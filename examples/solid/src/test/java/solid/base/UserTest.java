package solid.base;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserTest {
	@Test
	public void testCreation() {
		final String expectedLoginName = "noname";

		User user = new User(expectedLoginName);

		assertThat(user.getLoginName(), is(expectedLoginName));
	}
}