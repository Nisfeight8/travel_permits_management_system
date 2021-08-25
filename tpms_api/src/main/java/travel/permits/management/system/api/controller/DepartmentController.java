package travel.permits.management.system.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import travel.permits.management.system.api.dto.DepartmentDto;
import travel.permits.management.system.api.model.Department;

import travel.permits.management.system.api.service.DepartmentService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
	private DepartmentService departmentService;

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping
	@ResponseBody
	public ArrayList<Department> departments() {
		return departmentService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<Department> newDepartment(@Valid @RequestBody DepartmentDto department) {
        return new ResponseEntity<Department>(departmentService.save(department), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PutMapping("/{id}")
	@ResponseBody
	public Department updateDepartment(@RequestBody @Valid DepartmentDto department,@PathVariable("id") int id) {
        return departmentService.update(department,id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping("/{id}")
	@ResponseBody
	public Department getDepartment(@PathVariable("id") int id)  {
		return departmentService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteDepartment(@PathVariable("id") int id) {
		return new ResponseEntity<>(departmentService.delete(id), HttpStatus.OK);
	}
}