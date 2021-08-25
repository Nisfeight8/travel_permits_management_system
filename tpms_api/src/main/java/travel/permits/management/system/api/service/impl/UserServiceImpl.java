package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.ChangeDetailsDto;
import travel.permits.management.system.api.dto.UserDto;
import travel.permits.management.system.api.exception.CustomException;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.User;
import travel.permits.management.system.api.model.enums.RoleName;
import travel.permits.management.system.api.repository.UserRepository;
import travel.permits.management.system.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncode;

    @Override
    public User save(UserDto userDto) {
        if ((userRepository.existsByUsername(userDto.getUsername()))) {
            throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            user.setAuthority(userDto.getAuthority());
            user.setUsername(userDto.getUsername());
            user.setFullName(userDto.getFullName());
            user.setPassword(passwordEncode.encode(userDto.getPassword()));
            if (user.getAuthority().getAuthority().equals(RoleName.ROLE_EMPLOYEE.toString())
                    || user.getAuthority().getAuthority().equals(RoleName.ROLE_SUPERVISOR.toString())) {
                user.setEnabled(false);
            }
            return userRepository.save(user);
        }
    }

    @Override
    public int delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return id;
        } else {
            throw new EntityNotFoundException(User.class, "id", String.valueOf(id));
        }

    }

    @Override
    public User update(UserDto userDto, int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", String.valueOf(id)));
        if ((!user.getUsername().equals(userDto.getUsername()))
                && (userRepository.existsByUsername(userDto.getUsername()))) {
            throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
        } else {
            user.setUsername(userDto.getUsername());
            user.setFullName(userDto.getFullName());
            return userRepository.save(user);
        }
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", String.valueOf(id)));
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public ArrayList<User> findByAuthority(String authority) {
        return (ArrayList<User>) userRepository.findByAuthorityAuthority(RoleName.getByRoleName(authority)).stream()
                .filter(user -> user.isEnabled() == false).collect(Collectors.toList());

    }

    @Override
    public User changeDetails(ChangeDetailsDto cd, User user) {
        if ((!user.getUsername().equals(cd.getUsername())) && (userRepository.existsByUsername(cd.getUsername()))) {
            throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
        } else {
            user.setUsername(cd.getUsername());
            user.setFullName(cd.getFullName());
            return userRepository.save(user);
        }
    }

}