package travel.permits.management.system.api.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import travel.permits.management.system.api.model.TravelRequest;

public interface TravelRequestRepository extends CrudRepository<TravelRequest, Integer> {

    ArrayList<TravelRequest> findByEmployeeDepartmentName(String name);
    ArrayList<TravelRequest> findByPermitDate(String date);

}