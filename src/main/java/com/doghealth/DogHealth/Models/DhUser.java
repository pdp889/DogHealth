package com.doghealth.DogHealth.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DhUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String username;
    private String password;
    private String email;

    public DhUser(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @OneToMany(mappedBy = "dhUser")
    private Set<DhDog> dogs;

    public DhUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
