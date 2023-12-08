package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Security {

    //Security er til at tjekke username og password
    @Id
    private int id;
    private String username;
    private String password;

    public Security(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Security() {
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

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
