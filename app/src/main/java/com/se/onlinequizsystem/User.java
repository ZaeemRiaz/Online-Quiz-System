package com.se.onlinequizsystem;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer uType;

    public User() {
        id = null;
        username = null;
        password = null;
        uType = null;
    }

    public User(Integer id, String username, String password, Integer uType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.uType = uType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUType() {
        return this.uType;
    }

    public void setUType(Integer uType) {
        this.uType = uType;
    }

}
