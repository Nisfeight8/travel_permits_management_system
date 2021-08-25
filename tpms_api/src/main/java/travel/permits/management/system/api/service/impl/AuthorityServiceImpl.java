package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.AuthorityDto;
import travel.permits.management.system.api.exception.CustomException;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.Authority;
import travel.permits.management.system.api.model.User;
import travel.permits.management.system.api.model.enums.RoleName;
import travel.permits.management.system.api.repository.AuthorityRepository;
import travel.permits.management.system.api.repository.UserRepository;
import travel.permits.management.system.api.service.AuthorityService;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
	UserRepository userRepository;

    @Override
    public Authority save(AuthorityDto authorityDto) {
        Authority authority=new Authority();
        authority.setAuthority(RoleName.getByRoleName(authorityDto.getName()));
        return authorityRepository.save(authority);
    }

    @Override
    public int delete(int id) {
        if(authorityRepository.existsById(id)){
            for (User user : userRepository.findAll()) {
				if (user.getAuthority().getAuthority().equals(authorityRepository.findById(id).get().getAuthority())) {
					throw new CustomException("You cant delete this authority.This authority has users!", HttpStatus.BAD_REQUEST);
				}
			}
            authorityRepository.deleteById(id);
            return id;
        }else{
            throw new EntityNotFoundException(Authority.class, "id",String.valueOf(id));
        }


    }


    @Override
    public Authority findById(int id) {
        return authorityRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Authority.class, "id", String.valueOf(id)));

    }

    @Override
    public ArrayList<Authority> findAll() {
        ArrayList<Authority> authorities = new ArrayList<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

}