package travel.permits.management.system.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import travel.permits.management.system.api.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements
        ConstraintValidator<UniqueUsername, String> {

    @Override
    public void initialize(UniqueUsername username) {
    }
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String usernameField,
                           ConstraintValidatorContext cxt) {

        return !userRepository.existsByUsername(usernameField);

    }

}
