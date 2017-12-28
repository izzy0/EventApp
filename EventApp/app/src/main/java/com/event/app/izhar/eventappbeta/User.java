package com.event.app.izhar.eventappbeta;

public class User {

    private static int userId;
    private static String username;
    private static String firstName;
    private static String lastName;
    private String password;
    private static String email;
    private Boolean isHost;
    private Boolean isAdmin;

    //TODO should i add password?
    public User(int userId, String username, String password, String firstName, String lastName, String email) {

        User.userId = userId;
        User.username = username;
        this.password = password;
        User.firstName = firstName;
        User.lastName = lastName;
        User.email = email;
    }

    public User(int userId, String username, String firstName, String lastName, String email) {

        User.userId = userId;
        User.username = username;
        User.firstName = firstName;
        User.lastName = lastName;
        User.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return email;
    }

    public void setPassword(String email) {
        this.email = email;
    }

}
