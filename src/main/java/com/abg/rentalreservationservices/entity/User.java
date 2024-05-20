package com.abg.rentalreservationservices.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String drivingLicenceNumber;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
