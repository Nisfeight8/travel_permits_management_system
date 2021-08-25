package travel.permits.management.system.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import travel.permits.management.system.api.model.enums.RoleName;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
    @Enumerated(EnumType.STRING)
    @Column(unique=true)
    private RoleName authority;

    @Override
    public String getAuthority() {
        return this.authority.toString();
    }
}

