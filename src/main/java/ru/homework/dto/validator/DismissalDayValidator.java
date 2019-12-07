package ru.homework.dto.validator;

import ru.homework.dto.EmployeeView;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DismissalDayValidator implements ConstraintValidator<DismissalDay, EmployeeView> {
    @Override
    public boolean isValid(EmployeeView value, ConstraintValidatorContext context) {
        return value.getEmploymentDay().before(value.getDismissalDay());
    }
}
