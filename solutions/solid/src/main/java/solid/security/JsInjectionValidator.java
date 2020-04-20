package solid.security;

import solid.base.Validator;

public class JsInjectionValidator implements Validator {
    @Override
    public boolean isValid(String userName) {
        return (userName != null && !userName.contains("<script>"));
    }
}
