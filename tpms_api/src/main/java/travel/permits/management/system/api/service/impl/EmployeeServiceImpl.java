package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.EmployeeDto;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.Employee;
import travel.permits.management.system.api.repository.EmployeeRepository;
import travel.permits.management.system.api.repository.UserRepository;
import travel.permits.management.system.api.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
	private EmployeeRepository employeeRepository;
    @Autowired
	private UserRepository userRepository;

    @Override
    public Employee save(EmployeeDto employeeDto) {
        Employee employee =new Employee();
        employee.setDepartment(employeeDto.getDepartment());
        employee.setUser(userRepository.findById(employeeDto.getUser().getId()).get());
        return employeeRepository.save(employee);
    }

    @Override
    public int delete(int id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Employee.class, "id", String.valueOf(id)));
        employee.setUser(null);
        employeeRepository.delete(employee);
        return id;
    }

    @Override
    public Employee update(EmployeeDto employeeDto,int id) {
        Employee employee =employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Employee.class, "id", String.valueOf(id)));
        employee.setDepartment(employeeDto.getDepartment());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Employee.class, "id", String.valueOf(id)));
    }

    @Override
    public ArrayList<Employee> findAll() {
        ArrayList<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public ArrayList<Employee> findAllByDepartment(String name) {
        return employeeRepository.findByDepartmentName(name);
    }


}