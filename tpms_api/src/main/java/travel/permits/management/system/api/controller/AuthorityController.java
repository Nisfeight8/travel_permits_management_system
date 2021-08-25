package travel.permits.management.system.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import travel.permits.management.system.api.dto.AuthorityDto;
import travel.permits.management.system.api.model.Authority;
import travel.permits.management.system.api.service.AuthorityService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/authorities")
public class AuthorityController {
    @Autowired
	private AuthorityService authorityService;
    @Autowired
	PasswordEncoder passwordEncode;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
	@ResponseBody
	public ArrayList<Authority> authorities() {
		return authorityService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<Authority> newAuthority(@Valid @RequestBody AuthorityDto authority) {
        return new ResponseEntity<Authority>(authorityService.save(authority), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
	@ResponseBody
	public Authority getAuthority(@PathVariable("id") int id)  {
		return authorityService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteAuthority(@PathVariable("id") int id) {
		return new ResponseEntity<>(authorityService.delete(id), HttpStatus.OK);
	}
}