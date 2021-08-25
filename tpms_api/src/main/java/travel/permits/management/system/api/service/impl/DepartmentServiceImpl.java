package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.DepartmentDto;
import travel.permits.management.system.api.exception.CustomException;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.Department;
import travel.permits.management.system.api.model.Employee;
import travel.permits.management.system.api.model.Supervisor;
import travel.permits.management.system.api.repository.DepartmentRepository;
import travel.permits.management.system.api.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department save(DepartmentDto departmentDto) {
        if (((departmentRepository.existsByName(departmentDto.getName())))) {
            throw new CustomException("Name is already in use", HttpStatus.BAD_REQUEST);
        } else {
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setPlace(departmentDto.getPlace());
            return departmentRepository.save(department);
        }
    }

    @Override
    public int delete(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class, "id", String.valueOf(id)));
        for (Employee emp : department.getEmployees()) {
            emp.setUser(null);
        }
        Supervisor supervisor = department.getSupervisor();
        supervisor.setUser(null);
        departmentRepository.delete(department);
        return id;
    }

    @Override
    public Department update(DepartmentDto departmentDto, int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class, "id", String.valueOf(id)));
        if ((!department.getName().equals(departmentDto.getName())
                && (departmentRepository.existsByName(departmentDto.getName())))) {
            throw new CustomException("Name is already in use", HttpStatus.BAD_REQUEST);
        } else {
            department.setName(departmentDto.getName());
            department.setPlace(departmentDto.getPlace());
            return departmentRepository.save(department);
        }
    }

    @Override
    public Department findById(int id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Department.class, "id", String.valueOf(id)));
    }

    @Override
    public ArrayList<Department> findAll() {
        ArrayList<Department> deps = new ArrayList<>();
        departmentRepository.findAll().forEach(deps::add);
        return deps;
    }

}