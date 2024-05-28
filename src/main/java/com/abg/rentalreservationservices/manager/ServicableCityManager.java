package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.repository.ServicableCityRepository;
import com.abg.rentalreservationservices.service.ServicableCityService;
import exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicableCityManager implements ServicableCityService {

    private final ServicableCityRepository servicableCityRepository;

    public ServicableCityManager(ServicableCityRepository servicableCityRepository) {
        this.servicableCityRepository = servicableCityRepository;
    }

    @Override
    public List<ServicableCity> getAllServicableCities() {
        return servicableCityRepository.findAll();
    }

    @Override
    public ServicableCity getServicableCityByName(String sourceCity) {
        ServicableCity servicableCity =  servicableCityRepository.findByName(sourceCity);
        if(servicableCity==null){
            throw new NotFound("city not found");
        }
        return servicableCity;
    }

    @Override
    public ServicableCity getServicableCityById(Long sourceCity) {
        return servicableCityRepository.findById(sourceCity).orElse(null);
    }

}
