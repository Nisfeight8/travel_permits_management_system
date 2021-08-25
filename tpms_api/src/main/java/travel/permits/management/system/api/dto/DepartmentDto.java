package travel.permits.management.system.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DepartmentDto {
    @NotEmpty
    @NotNull
    @Size(max = 20, min = 3, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotEmpty
    @NotNull
    @Size(max = 20, min = 3, message = "Place must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Place must contain only letters !")
    private String place;

    public DepartmentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



}