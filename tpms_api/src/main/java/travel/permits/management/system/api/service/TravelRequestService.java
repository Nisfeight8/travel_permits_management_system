package travel.permits.management.system.api.service;

import java.util.ArrayList;

import travel.permits.management.system.api.dto.TravelRequestDto;
import travel.permits.management.system.api.model.TravelRequest;

public interface TravelRequestService {
    TravelRequest save(TravelRequestDto travelRequest);

    int delete(int id);

    TravelRequest update(TravelRequestDto travelRequest,int id);

    TravelRequest findById(int id);

    ArrayList<TravelRequest> findAll();

    ArrayList<TravelRequest> findAllByDepartment(String name);

    ArrayList<TravelRequest> findAllByDate(String date);



}