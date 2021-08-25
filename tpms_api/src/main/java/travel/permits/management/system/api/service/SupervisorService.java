package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.SupervisorDto;
import travel.permits.management.system.api.model.Supervisor;

public interface SupervisorService {
    Supervisor save(SupervisorDto supervisor);

    int delete(int id);

    Supervisor update(SupervisorDto supervisor,int id);

    Supervisor findById(int id);

    Supervisor findByDepartment(String name);

    ArrayList<Supervisor> findAll();
}