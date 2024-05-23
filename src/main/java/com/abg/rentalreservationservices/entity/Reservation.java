package com.abg.rentalreservationservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ServicableCity sourceCity;

    @OneToOne
    private ServicableCity destinationCity;

    private String pickUpAddress;

    private String dropOffAddress;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Integer seatingCapacity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    private BigDecimal amountRecieved;
}
