package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.DepartmentDto;
import travel.permits.management.system.api.model.Department;

public interface DepartmentService {
    Department save(DepartmentDto department);

    int delete(int id);

    Department update(DepartmentDto department,int id);

    Department findById(int id);

    ArrayList<Department> findAll();




}