package ru.homework.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = SalaryValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface Salary {
    String message() default "Employee salary must be less than chief's salary";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
