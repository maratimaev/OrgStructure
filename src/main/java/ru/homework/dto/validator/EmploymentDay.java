package ru.homework.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EmploymentDayValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface EmploymentDay {
    String message() default "Employment day must be after birthday";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
