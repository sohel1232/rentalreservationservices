package com.abg.rentalreservationservices.repository;

import com.abg.rentalreservationservices.entity.ServicableCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicableCityRepository extends JpaRepository<ServicableCity,Long> {
}
