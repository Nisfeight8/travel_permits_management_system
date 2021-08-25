package travel.permits.management.system.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

import travel.permits.management.system.api.dto.ChangeDetailsDto;
import travel.permits.management.system.api.dto.PasswordChangerDto;
import travel.permits.management.system.api.dto.UserDto;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.User;
import travel.permits.management.system.api.service.UserService;
import travel.permits.management.system.api.service.impl.CustomUserDetailsService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    @Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIRECTOR') ")
	@GetMapping
	@ResponseBody
	public ArrayList<User> employees(@RequestParam(defaultValue = "empty") String authority) throws EntityNotFoundException {
		if (!authority.equals("empty")) {
			return userService.findByAuthority(authority);
		}

		return userService.findAll();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
	@ResponseBody
	public ResponseEntity<User> newUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
	@ResponseBody
	public User updateUser(@RequestBody @Valid UserDto user,@PathVariable("id") int id ) {
        return userService.update(user,id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") int id) {
		return userService.findById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
	}

	@GetMapping("/whoami")
    public User user() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

	@PutMapping("/changeDetails")
    public User ChangeDetails(@RequestBody @Valid ChangeDetailsDto cd) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.changeDetails(cd, user);
    }
	@PostMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerDto passwordChanger) {
        jwtUserDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "Success password changed !" );
        return ResponseEntity.accepted().body(result);
    }
}