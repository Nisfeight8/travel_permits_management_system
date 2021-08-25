package travel.permits.management.system.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswordChangerDto {
    @NotEmpty
    public String oldPassword;
    @NotEmpty
    @Size(min = 5, message = "Password must contain at least 5 characters")
    public String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }



}