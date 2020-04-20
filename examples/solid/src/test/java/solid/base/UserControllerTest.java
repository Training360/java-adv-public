package solid.base;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserControllerTest {

	class TestUserServiceImplementation implements UserService {

		private User parameterOfLastMethodCall;

		@Override
		public void save(User user) {
			parameterOfLastMethodCall = user;
		}

		public User getParameterOfLastMethodCall() {
			return parameterOfLastMethodCall;
		}
	}

	class TestValidatorImplementation implements Validator {

		private boolean returnValueOfIsValid;

		private String parameterOfLastMethodCall;

		public TestValidatorImplementation(boolean returnValueOfIsValid) {
			this.returnValueOfIsValid = returnValueOfIsValid;
		}

		@Override
		public boolean isValid(String userName) {
			parameterOfLastMethodCall = userName;
			return returnValueOfIsValid;
		}

		public String getParameterOfLastMethodCall() {
			return parameterOfLastMethodCall;
		}

	}

	@Test
	public void testCreateUserWithValidUserName() {

		final String validUsername = "123456";
		List<Validator> validators = new ArrayList<>();
		TestValidatorImplementation dummyTrueValidator = new TestValidatorImplementation(true);
		validators.add(dummyTrueValidator);

		TestUserServiceImplementation userService = new TestUserServiceImplementation();

		UserController userController = new UserController(userService, validators);
		userController.createUser(validUsername);

		assertThat(dummyTrueValidator.getParameterOfLastMethodCall() , is(validUsername));
		assertThat(userService.getParameterOfLastMethodCall() , is(notNullValue()));
		assertThat(userService.getParameterOfLastMethodCall().getLoginName() , is(validUsername));
	}


	@Test
	public void testCreateUserWithInvalidUserName() {

		final String invalidUsername = "123 456";
		List<Validator> validators = new ArrayList<>();
		TestValidatorImplementation dummyFalseValidator = new TestValidatorImplementation(false);
		validators.add(dummyFalseValidator);

		TestUserServiceImplementation userService = new TestUserServiceImplementation();

		UserController userController = new UserController(userService, validators);
		userController.createUser(invalidUsername);

		assertThat(userService.getParameterOfLastMethodCall() , is(nullValue()));
	}

	@Test
	public void testCreateUserWithInvalidUserNameMultipleValidators() {

		final String input = "asdasd";
		List<Validator> validators = new ArrayList<>();
		Validator dummyTrueValidator =  new TestValidatorImplementation(true);
		validators.add(dummyTrueValidator);
		Validator dummyFalseValidator =  new TestValidatorImplementation(false);
		validators.add(dummyFalseValidator);

		TestUserServiceImplementation userService = new TestUserServiceImplementation();

		UserController userController = new UserController(userService, validators);
		userController.createUser(input);

		assertThat(userService.getParameterOfLastMethodCall() , is(nullValue()));
	}
}