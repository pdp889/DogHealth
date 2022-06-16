package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
public class DhFeeding {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    private DhFoodType dhFoodType;

    @ManyToOne()
    private DhDog dhDog;

    private LocalDate date;

    private Double quantityInOz;

    public DhFeeding(DhFoodType dhFoodType, DhDog dhDog, String date, Double quantityInOz) {
        this.dhFoodType = dhFoodType;
        this.dhDog = dhDog;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        this.date = LocalDate.parse(date, formatter);
        this.quantityInOz = quantityInOz;
    }
    public DhFeeding(DhFoodType dhFoodType, DhDog dhDog, LocalDate date, Double quantityInOz) {
        this.dhFoodType = dhFoodType;
        this.dhDog = dhDog;
        this.date = date;
        this.quantityInOz = quantityInOz;
    }
    public DhFeeding(){}

    public DhFoodType getDhFoodType() {
        return dhFoodType;
    }

    public void setDhFoodType(DhFoodType dhFoodType) {
        this.dhFoodType = dhFoodType;
    }

    public DhDog getDhDog() {
        return dhDog;
    }

    public void setDhDog(DhDog dhDog) {
        this.dhDog = dhDog;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getQuantityInOz() {
        return quantityInOz;
    }

    public void setQuantityInOz(Double quantityInOz) {
        this.quantityInOz = quantityInOz;
    }
}
