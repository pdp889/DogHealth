package com.doghealth.DogHealth.Models;

import javax.persistence.*;

@Entity
public class DhWeight {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private double weight;

    @ManyToOne()
    private DhDog dhDog;

    private String date;

    public DhWeight(double weight, DhDog dog, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
