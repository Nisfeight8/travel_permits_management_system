package travel.permits.management.system.api.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleName {
	ROLE_ADMIN,
    ROLE_SUPERVISOR,
	ROLE_EMPLOYEE,
	ROLE_DIRECTOR;
	public static RoleName getByRoleName(String rolename) {
        for (RoleName name : RoleName.values()) {
            if (name.name().equals(rolename)) {
                return name;
            }
        }
        return null;
    }
}
