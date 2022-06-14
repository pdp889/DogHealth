package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DhFoodType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private double caloriesPerOz;

    private String name;

    @OneToMany(mappedBy = "dhFoodType")
    private Set<DhFeeding> feedings;

    public DhFoodType(double caloriesPerOz, String name) {
        this.caloriesPerOz = caloriesPerOz;
        this.name = name;
    }

    public DhFoodType(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCaloriesPerOz() {
        return caloriesPerOz;
    }

    public void setCaloriesPerOz(double caloriesPerOz) {
        this.caloriesPerOz = caloriesPerOz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
