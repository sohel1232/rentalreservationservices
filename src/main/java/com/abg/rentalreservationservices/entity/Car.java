package com.abg.rentalreservationservices.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String plate;

    private String type;

    private String seatingCapacity;

    private BigDecimal basePrice;

    @OneToOne(mappedBy = "car")
    private Reservation reservation;

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private ServicableCity city;
}
