package travel.permits.management.system.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import travel.permits.management.system.api.model.enums.RoleName;
import travel.permits.management.system.api.repository.AuthorityRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleNameValidator implements
        ConstraintValidator<UniqueRoleName, String> {

    @Override
    public void initialize(UniqueRoleName roleName) {
    }
    @Autowired
    AuthorityRepository authRepository;

    @Override
    public boolean isValid(String roleNameField,
                           ConstraintValidatorContext cxt) {

        return !authRepository.existsByAuthority(RoleName.getByRoleName(roleNameField))&&RoleName.getByRoleName(roleNameField)!=null;

    }
}
