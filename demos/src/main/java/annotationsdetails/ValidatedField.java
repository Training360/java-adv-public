package annotationsdetails;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatedField {

    String value() default "unknown";

    Class<? extends Validator>[] validators() default {};
}
