package com.doghealth.DogHealth.Models;

import javax.persistence.*;

@Entity
public class DhExercise {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    private DhExerciseType dhExerciseType;

    @ManyToOne()
    private DhDog dhDog;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private double durationInHours;

    public DhExercise(DhDog dhDog, DhExerciseType dhExerciseType, double durationInHours, String date) {
        this.dhExerciseType = dhExerciseType;
        this.durationInHours = durationInHours;
        this.dhDog = dhDog;
        this.date = date;
    }

    public DhExercise(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DhExerciseType getDhExerciseType() {
        return dhExerciseType;
    }

    public void setDhExerciseType(DhExerciseType dhExerciseType) {
        this.dhExerciseType = dhExerciseType;
    }

    public double getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(double durationInHours) {
        this.durationInHours = durationInHours;
    }

    public DhDog getDhDog() {
        return dhDog;
    }

    public void setDhDog(DhDog dhDog) {
        this.dhDog = dhDog;
    }
}
