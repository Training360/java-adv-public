package annotationsrepeating;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatedFields {

    ValidatedField[] value();
}
