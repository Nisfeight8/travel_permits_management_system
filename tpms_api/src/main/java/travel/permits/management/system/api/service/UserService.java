package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.ChangeDetailsDto;
import travel.permits.management.system.api.dto.UserDto;
import travel.permits.management.system.api.model.User;

public interface UserService {
    User save(UserDto user);

    int delete(int id);

    User update(UserDto user,int id);

    User findById(int id);

    ArrayList<User> findAll();

    ArrayList<User> findByAuthority(String authority);

    User changeDetails(ChangeDetailsDto cd, User user);




}