package com.abg.rentalreservationservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String plate;

    private String type;

    private Integer seatingCapacity;

    private BigDecimal basePrice;

    private String imageName;

    @OneToMany(mappedBy = "car")
    private  List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private ServicableCity city;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plate='" + plate + '\'' +
                ", type='" + type + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", basePrice=" + basePrice +
                '}';
    }
}
