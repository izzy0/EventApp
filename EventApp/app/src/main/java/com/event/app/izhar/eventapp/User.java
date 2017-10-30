package com.event.app.izhar.eventapp;

/**
 * Created by Izhar on 10/7/2017.
 *
 * create user entity Object?
 *
 */

public class User {
    private String username;
    private String firstName;
    private String password;
    private String email;
    private Boolean isHost;
    private Boolean isAdmin;

    //add password?
    User(String username, String firstName, String password, String email, Boolean isHost, Boolean isAdmin){
        this.username = username;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.isHost = isHost;
        this.isAdmin = isAdmin;
    }

    User(){}

    //getter-setters
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Boolean isHost(){
        return isHost;
    }

    public void setIsHost(Boolean isHost){
        this.isHost = isHost;
    }

    public Boolean isAdmin(){
        return isAdmin;
    }

    public void setIsAdmin (Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
    //end of getter-setter
}
