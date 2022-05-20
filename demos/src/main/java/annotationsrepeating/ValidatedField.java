package annotationsrepeating;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidatedFields.class)
public @interface ValidatedField {

    Class<? extends Validator> value();

}
