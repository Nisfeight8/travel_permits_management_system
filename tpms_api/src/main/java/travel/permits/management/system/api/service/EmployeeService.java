package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.EmployeeDto;
import travel.permits.management.system.api.model.Employee;

public interface EmployeeService {
    Employee save(EmployeeDto employee);

    int delete(int id);

    Employee update(EmployeeDto employee,int id);

    Employee findById(int id);

    ArrayList<Employee> findAll();

    ArrayList<Employee> findAllByDepartment(String name);

}