package com.xloop.carservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cardetails")
@NoArgsConstructor
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String short_dsrp;

    @Column(columnDefinition = "TEXT")
    private String long_dsrp;
    private String image;
    private long rental_fee_per_day;

    public Car(String name, String short_dsrp, String long_dsrp, String image, long rental_fee_per_day){
        this.name = name;
        this.short_dsrp = short_dsrp;
        this.long_dsrp = long_dsrp;
        this.image = image;
        this.rental_fee_per_day = rental_fee_per_day;


    }
}
