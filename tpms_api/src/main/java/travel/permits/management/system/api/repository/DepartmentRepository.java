package travel.permits.management.system.api.repository;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.Department;

public interface DepartmentRepository extends CrudRepository<Department,Integer> {

    public  Department findByName(String name);
    public Boolean existsByName(String name);
}