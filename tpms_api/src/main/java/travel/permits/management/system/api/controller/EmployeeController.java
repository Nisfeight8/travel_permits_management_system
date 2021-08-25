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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import travel.permits.management.system.api.dto.EmployeeDto;
import travel.permits.management.system.api.model.Employee;
import travel.permits.management.system.api.service.EmployeeService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
	private EmployeeService employeeService;

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping
	@ResponseBody
	public ArrayList<Employee> employees(@RequestParam(defaultValue = "empty") String department) {
		if (!department.equals("empty")) {
			return employeeService.findAllByDepartment(department);
		}
		return employeeService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<Employee> newEmployee(@Valid @RequestBody EmployeeDto employee) {
        return new ResponseEntity<Employee>(employeeService.save(employee), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PutMapping("/{id}")
	@ResponseBody
	public Employee updateEmployee(@Valid @RequestBody EmployeeDto employee,@PathVariable("id") int id) {
        return employeeService.update(employee,id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping("/{id}")
	@ResponseBody
	public Employee getEmployee(@PathVariable("id") int id)  {
		return employeeService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id)  {
		return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
	}
}