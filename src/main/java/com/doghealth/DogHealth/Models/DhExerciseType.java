package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DhExerciseType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private double caloriesBurnedPerHour;

    private String name;

    @OneToMany(mappedBy = "dhExerciseType")
    private Set<DhExercise> exercises;

    public DhExerciseType(double caloriesBurnedPerHour, String name, Set<DhExercise> exercises) {
        this.caloriesBurnedPerHour = caloriesBurnedPerHour;
        this.name = name;
        this.exercises = exercises;
    }

    public DhExerciseType(double caloriesBurnedPerHour, String name) {
        this.caloriesBurnedPerHour = caloriesBurnedPerHour;
        this.name = name;
    }

    public DhExerciseType(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCaloriesBurnedPerHour() {
        return caloriesBurnedPerHour;
    }

    public void setCaloriesBurnedPerHour(double caloriesBurnedPerHour) {
        this.caloriesBurnedPerHour = caloriesBurnedPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
