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

import travel.permits.management.system.api.dto.SupervisorDto;
import travel.permits.management.system.api.model.Supervisor;
import travel.permits.management.system.api.service.SupervisorService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/supervisors")
public class SupervisorController {
    @Autowired
	private SupervisorService supervisorService;

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping
	@ResponseBody
	public ArrayList<Supervisor> supervisors()  {
		return supervisorService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<Supervisor> newSupervisor(@Valid @RequestBody SupervisorDto supervisor) {
        return new ResponseEntity<Supervisor>(supervisorService.save(supervisor), HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @PutMapping("/{id}")
	@ResponseBody
	public Supervisor updateSupervisor(@Valid @RequestBody SupervisorDto supervisor,@PathVariable("id") int id) {
        return supervisorService.update(supervisor,id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR') or hasRole('ROLE_SUPERVISOR')")
    @GetMapping("/{id}")
	@ResponseBody
	public Supervisor getSupervisor(@PathVariable("id") int id) {
		return supervisorService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping("/department/{dep_name}")
	@ResponseBody
	public Supervisor getSupervisorByDepartmentName(@PathVariable("dep_name") String dep_name) {
		return supervisorService.findByDepartment(dep_name);
	}

	@PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteSupervisor(@PathVariable("id") int id) {
		return new ResponseEntity<>(supervisorService.delete(id), HttpStatus.OK);
	}
}