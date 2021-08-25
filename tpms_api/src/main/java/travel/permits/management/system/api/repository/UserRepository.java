package travel.permits.management.system.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.User;
import travel.permits.management.system.api.model.enums.RoleName;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
	ArrayList<User> findByAuthorityAuthority(RoleName role);
	Boolean existsByUsername(String username);

}
