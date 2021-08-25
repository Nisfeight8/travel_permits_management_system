package travel.permits.management.system.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TravelRequestDto {
    private boolean approved;
    @NotEmpty
    @NotNull
    private String permitDuration;
    @NotNull
    private boolean fromWorkPlace;
    @NotEmpty
    @NotNull
    private String permitDate;
    @NotEmpty
    private String address;
    @NotEmpty
    @NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[0-9]+", message = "Mobile number is invalid!!")
    private String phone;
    @NotNull
    private int id;


    public TravelRequestDto() {
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getPermitDuration() {
        return permitDuration;
    }

    public void setPermitDuration(String permitDuration) {
        this.permitDuration = permitDuration;
    }

    public boolean isFromWorkPlace() {
        return fromWorkPlace;
    }

    public void setFromWorkPlace(boolean fromWorkPlace) {
        this.fromWorkPlace = fromWorkPlace;
    }

    public String getPermitDate() {
        return permitDate;
    }

    public void setPermitDate(String permitDate) {
        this.permitDate = permitDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}