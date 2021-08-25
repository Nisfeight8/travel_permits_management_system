package travel.permits.management.system.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

    ArrayList<Employee> findByDepartmentName(String name);
}