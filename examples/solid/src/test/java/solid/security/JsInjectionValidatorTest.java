package solid.security;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsInjectionValidatorTest {
	@Test
	public void testNotValidWithNull() throws Exception {
		JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

		assertThat(jsInjectionValidator.isValid(null), is(false));
	}
	@Test
	public void testIsValidWithEmptyString() throws Exception {
		JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

		assertThat(jsInjectionValidator.isValid(""), is(true));
	}
	@Test
	public void testIsValidWithEvilInput() throws Exception {
		JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

		assertThat(jsInjectionValidator.isValid("sdfasdf<script>"), is(false));
	}
	@Test
	public void testIsValidWithValidInput() throws Exception {
		JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

		assertThat(jsInjectionValidator.isValid("asdfasdf<adfg>sdfasdf"), is(true));
	}
}