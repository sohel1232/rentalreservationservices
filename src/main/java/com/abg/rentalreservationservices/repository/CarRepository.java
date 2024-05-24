package com.abg.rentalreservationservices.repository;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "SELECT c FROM Car c " +
            "WHERE c.city.name = :sourceCity AND " +
            "c.seatingCapacity = :seatingCapacity",
            nativeQuery = false)
    List<Car> findAvailableCarsByCityAndCapacity(@Param("sourceCity") String sourceCity,
                                                 @Param("seatingCapacity") Integer seatingCapacity);

}
