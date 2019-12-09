package ru.homework.dto.validator;

import ru.homework.dto.EmployeeView;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации @EmploymentDay
 */
public class EmploymentDayValidator implements ConstraintValidator<EmploymentDay, EmployeeView> {
    @Override
    public boolean isValid(EmployeeView value, ConstraintValidatorContext context) {
        return value.getBirthday().before(value.getEmploymentDay());
    }
}
