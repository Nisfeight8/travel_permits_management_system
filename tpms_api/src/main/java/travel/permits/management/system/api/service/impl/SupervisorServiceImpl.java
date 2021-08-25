package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.SupervisorDto;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.Department;
import travel.permits.management.system.api.model.Supervisor;
import travel.permits.management.system.api.repository.DepartmentRepository;
import travel.permits.management.system.api.repository.SupervisorRepository;
import travel.permits.management.system.api.repository.UserRepository;
import travel.permits.management.system.api.service.SupervisorService;

@Service
@Transactional
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
	private SupervisorRepository supervisorRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private DepartmentRepository departmentRepository;


    @Override
    public Supervisor save(SupervisorDto supervisorDto) {
        Supervisor supervisor = new Supervisor();
        supervisor.setDepartment(supervisorDto.getDepartment());
        supervisor.setUser(userRepository.findById(supervisorDto.getUser().getId()).get());
        return supervisorRepository.save(supervisor);

    }

    @Override
    public int delete(int id) {
        Supervisor supervisor =supervisorRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Supervisor.class, "id", String.valueOf(id)));
        supervisorRepository.delete(supervisor);
        return id;
    }

    @Override
    public Supervisor update(SupervisorDto supervisorDto,int id) {
        Supervisor supervisor =supervisorRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Supervisor.class, "id", String.valueOf(id)));
        Department dep=departmentRepository.findByName(supervisorDto.getDepartment().getName());
        if(dep.getSupervisor()!=null){
            supervisorRepository.delete(dep.getSupervisor());
        }
        supervisor.setDepartment(supervisorDto.getDepartment());
        return supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor findById(int id) {
        return supervisorRepository.findById(id).orElseThrow(()->new EntityNotFoundException(Supervisor.class, "id", String.valueOf(id)));
    }

    @Override
    public ArrayList<Supervisor> findAll() {
        ArrayList<Supervisor> supervisors = new ArrayList<>();
        supervisorRepository.findAll().forEach(supervisors::add);
        return supervisors;
    }

    @Override
    public Supervisor findByDepartment(String name) {
        return supervisorRepository.findByDepartmentName(name);

    }

}