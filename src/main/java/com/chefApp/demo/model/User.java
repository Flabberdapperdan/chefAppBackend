package com.chefApp.demo.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;
    private String name;
    private String userName;

    // getters and setters \\
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
