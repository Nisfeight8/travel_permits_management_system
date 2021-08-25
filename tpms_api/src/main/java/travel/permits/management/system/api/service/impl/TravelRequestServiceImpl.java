package travel.permits.management.system.api.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.permits.management.system.api.dto.TravelRequestDto;
import travel.permits.management.system.api.exception.EntityNotFoundException;
import travel.permits.management.system.api.model.TravelRequest;
import travel.permits.management.system.api.model.enums.PermitDuration;
import travel.permits.management.system.api.repository.EmployeeRepository;
import travel.permits.management.system.api.repository.TravelRequestRepository;
import travel.permits.management.system.api.service.TravelRequestService;

@Service
@Transactional
public class TravelRequestServiceImpl implements TravelRequestService {

    @Autowired
	private TravelRequestRepository travelRequestRepository;

    @Autowired
	private EmployeeRepository employeeRepository;


    @Override
    public TravelRequest save(TravelRequestDto travelRequestDto) {
        TravelRequest travelRequest =new TravelRequest();
        if(travelRequestRepository.existsById(travelRequestDto.getId())){
            travelRequestRepository.deleteById(travelRequestDto.getId());
        }
        travelRequest.setApproved(false);
        travelRequest.setAddress(travelRequestDto.getAddress());
        travelRequest.setFromWorkPlace(travelRequestDto.isFromWorkPlace());
        travelRequest.setPermitDate(travelRequestDto.getPermitDate());
        travelRequest.setPermitDuration(PermitDuration.getByRemovalDuration(travelRequestDto.getPermitDuration()));
        travelRequest.setPhone(travelRequestDto.getPhone());
        travelRequest.setEmployee(employeeRepository.findById(travelRequestDto.getId()).get());
        return travelRequestRepository.save(travelRequest);
    }

    @Override
    public int delete(int id) {
        if(travelRequestRepository.existsById(id)){
            travelRequestRepository.deleteById(id);
            return id;
        }else{
            throw new EntityNotFoundException(TravelRequest.class, "id",String.valueOf(id));
        }
    }

    @Override
    public TravelRequest update(TravelRequestDto travelRequestDto,int id) {
        TravelRequest travelRequest =travelRequestRepository.findById(id).orElseThrow(()->new EntityNotFoundException(TravelRequest.class, "id", String.valueOf(id)));
        travelRequest.setApproved(travelRequestDto.isApproved());
        travelRequest.setAddress(travelRequestDto.getAddress());
        travelRequest.setFromWorkPlace(travelRequestDto.isFromWorkPlace());
        travelRequest.setPermitDate(travelRequestDto.getPermitDate());
        travelRequest.setPermitDuration(PermitDuration.getByRemovalDuration(travelRequestDto.getPermitDuration()));
        travelRequest.setPhone(travelRequestDto.getPhone());
        return travelRequestRepository.save(travelRequest);
    }

    @Override
    public TravelRequest findById(int id) {
        return travelRequestRepository.findById(id).orElseThrow(()->new EntityNotFoundException(TravelRequest.class, "id", String.valueOf(id)));
    }

    @Override
    public ArrayList<TravelRequest> findAll() {
        ArrayList<TravelRequest> trs = new ArrayList<>();
        travelRequestRepository.findAll().forEach(trs::add);
        return trs;
    }

    @Override
    public ArrayList<TravelRequest> findAllByDepartment(String name) {
        return travelRequestRepository.findByEmployeeDepartmentName(name);
    }

    @Override
    public ArrayList<TravelRequest> findAllByDate(String date) {
        return travelRequestRepository.findByPermitDate(date);
    }

}