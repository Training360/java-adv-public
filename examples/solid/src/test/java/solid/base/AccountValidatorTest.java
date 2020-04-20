package solid.base;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AccountValidatorTest {
	@Test
	public void testIsValidWithShortUsername() {
		AccountValidator accountValidator = new AccountValidator();

		assertThat(accountValidator.isValid("12345"), is(false));
	}

	@Test
	public void testIsValidWithWrongUsername() {
		AccountValidator accountValidator = new AccountValidator();

		assertThat(accountValidator.isValid("12 34 5"), is(false));
	}

	@Test
	public void testIsValidWitGoodUsername() {
		AccountValidator accountValidator = new AccountValidator();

		assertThat(accountValidator.isValid("123456"), is(true));
	}

	@Test
	public void testIsValidWithNull() {
		AccountValidator accountValidator = new AccountValidator();

		assertThat(accountValidator.isValid(null), is(false));
	}

	@Test
	public void testIsValidWithEmptyString() {
		AccountValidator accountValidator = new AccountValidator();

		assertThat(accountValidator.isValid(""), is(false));
	}
}