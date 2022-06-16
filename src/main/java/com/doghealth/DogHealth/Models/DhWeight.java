package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
public class DhWeight {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private double weight;

    @ManyToOne()
    private DhDog dhDog;

    private LocalDate date;

    public DhWeight(double weight, DhDog dog, String date) {
        this.weight = weight;
        this.dhDog = dog;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        this.date = LocalDate.parse(date, formatter);
    }

    public DhWeight(double weight, DhDog dog, LocalDate date) {
        this.weight = weight;
        this.dhDog = dog;
        this.date = date;
    }


    public DhWeight(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer weightID) {
        this.id = weightID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public DhDog getDhDog() {
        return dhDog;
    }

    public void setDhDog(DhDog dog) {
        this.dhDog = dog;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
