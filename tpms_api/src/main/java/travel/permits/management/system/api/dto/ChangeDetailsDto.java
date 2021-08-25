package travel.permits.management.system.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ChangeDetailsDto {
    @NotEmpty
    @Size(max = 20, min = 5, message = "Username must be between 5 and 20 characters")
    private String username;
    @NotEmpty
    @Size(max = 36, min = 5, message = "FullName must be between 5 and 36 characters")
    @Pattern(regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$", message = "FullName is invalid!!")
    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



}