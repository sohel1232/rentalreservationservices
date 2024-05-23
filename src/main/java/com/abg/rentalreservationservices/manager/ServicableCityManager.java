package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.repository.ServicableCityRepository;
import com.abg.rentalreservationservices.service.ServicableCityService;
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
        return servicableCityRepository.findByName(sourceCity);
    }

    @Override
    public ServicableCity getServicableCityById(Long sourceCity) {
        return servicableCityRepository.findById(sourceCity).orElse(null);
    }

    @Override
    public ServicableCity findCityByName(String city) {
        return servicableCityRepository.findByName(city);
    }
}
