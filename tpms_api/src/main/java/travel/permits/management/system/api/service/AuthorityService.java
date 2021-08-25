package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.AuthorityDto;
import travel.permits.management.system.api.model.Authority;

public interface AuthorityService {
    Authority save(AuthorityDto authority);

    int delete(int id);

    Authority findById(int id);

    ArrayList<Authority> findAll();
}