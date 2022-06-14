package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DhDog {

    private String dogName;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer gender;
    private Boolean fixed;

    @ManyToOne()
    private DhUser dhUser;

    @OneToMany(mappedBy = "dhDog")
    private Set<DhWeight> weights;

    @OneToMany(mappedBy = "dhDog")
    private Set<DhExercise> exercises;

    @OneToMany(mappedBy = "dhDog")
    private Set<DhFeeding> feedings;

    //constructors
    public DhDog(String dogName, Integer gender, Boolean fixed) {
        this.dogName = dogName;
        this.gender = gender;
        this.fixed = fixed;
    }

    public DhDog(String dogName, Integer gender, Boolean fixed, DhUser dhUser) {
        this.dogName = dogName;
        this.gender = gender;
        this.fixed = fixed;
        this.dhUser = dhUser;
    }

    public DhDog (){}


    //getters and setters
    public DhUser getDhUser() {
        return dhUser;
    }

    public void setDhUser(DhUser dhUser) {
        this.dhUser = dhUser;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String name) {
        this.dogName = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

}
