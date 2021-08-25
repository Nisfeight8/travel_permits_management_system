package travel.permits.management.system.api.repository;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.Authority;
import travel.permits.management.system.api.model.enums.RoleName;

public interface AuthorityRepository extends CrudRepository<Authority,Integer> {
    Authority findByAuthority(RoleName name);
    Boolean existsByAuthority(RoleName name);
}