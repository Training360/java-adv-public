package solid.base;


public class AccountValidator implements Validator {
	public boolean isValid(String userName) {
		return userName != null && userName.length() >= 6 && !userName.contains(" ");
	}
}
