package travel.permits.management.system.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import travel.permits.management.system.api.repository.DepartmentRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepNameValidator implements
        ConstraintValidator<UniqueDepName, String> {

    @Override
    public void initialize(UniqueDepName depName) {
    }

    @Autowired
    DepartmentRepository depRepository;

    @Override
    public boolean isValid(String depNameField,
                           ConstraintValidatorContext cxt) {

        return !depRepository.existsByName(depNameField);

    }
}
