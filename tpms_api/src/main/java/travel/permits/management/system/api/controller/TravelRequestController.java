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

import travel.permits.management.system.api.dto.TravelRequestDto;
import travel.permits.management.system.api.model.TravelRequest;
import travel.permits.management.system.api.service.TravelRequestService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/travelRequests")
public class TravelRequestController {

    @Autowired
	private TravelRequestService travelRequestService;

	@PreAuthorize("hasRole('ROLE_DIRECTOR') or hasRole('ROLE_SUPERVISOR')")
    @GetMapping
	@ResponseBody
	public ArrayList<TravelRequest> travelRequests(@RequestParam(defaultValue = "empty") String department, @RequestParam(defaultValue = "empty") String date)  {
		if (!department.equals("empty")) {
			return travelRequestService.findAllByDepartment(department);
		}
		if (!date.equals("empty")) {
			return travelRequestService.findAllByDate(date);
		}
		return travelRequestService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<TravelRequest> newTravelRequest(@Valid  @RequestBody TravelRequestDto travelRequest) {
        return new ResponseEntity<TravelRequest>(travelRequestService.save(travelRequest), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @PutMapping("/{id}")
	@ResponseBody
	public TravelRequest updateTravelRequest(@Valid @RequestBody TravelRequestDto travelRequest,@PathVariable("id") int id) {
        return travelRequestService.update(travelRequest,id);
	}
	@PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_SUPERVISOR')")
    @GetMapping("/{id}")
	@ResponseBody
	public TravelRequest getTravelRequest(@PathVariable("id") int id) {
		return travelRequestService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_SUPERVISOR')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteTravelRequest(@PathVariable("id") int id) {
		return new ResponseEntity<>(travelRequestService.delete(id), HttpStatus.OK);
	}
}