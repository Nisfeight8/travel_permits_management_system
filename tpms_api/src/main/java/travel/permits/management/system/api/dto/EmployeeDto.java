package travel.permits.management.system.api.dto;

import travel.permits.management.system.api.model.Department;
import travel.permits.management.system.api.model.User;

import javax.validation.constraints.NotNull;

public class EmployeeDto {
    @NotNull
    User user;
    @NotNull
    Department department;

    public EmployeeDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }




}