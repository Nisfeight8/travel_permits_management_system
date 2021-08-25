package travel.permits.management.system.api.repository;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.Supervisor;

public interface SupervisorRepository extends CrudRepository<Supervisor,Integer>{

    Supervisor findByDepartmentName(String name);
    Boolean existsByDepartmentName(String name);

}