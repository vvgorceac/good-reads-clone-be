package md.pentlaog.goodreadsclone.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ISBNValidation.class)
public @interface ISBN {
    String message() default "ISBN is not valid";
    Class<?>[] groups() default {};
    public abstract Class<?extends Payload> [] payload() default {};
}
