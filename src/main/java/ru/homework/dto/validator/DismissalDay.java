package ru.homework.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DismissalDayValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface DismissalDay {
    String message() default "Dismissal day must be after an employment day";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
