package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.ServicableCity;

import java.util.List;

public interface ServicableCityService {
    List<ServicableCity> getAllServicableCities();

    ServicableCity getServicableCityByName(String sourceCity);

    ServicableCity getServicableCityById(Long sourceCity);

}
