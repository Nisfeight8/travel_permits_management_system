package travel.permits.management.system.api.dto;


import travel.permits.management.system.api.validator.UniqueRoleName;

public class AuthorityDto {
    @UniqueRoleName
    private String name;

    public AuthorityDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}