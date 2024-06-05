package com.example.Xcrack.Service.Implementation;
import java.time.LocalDate;

public class UserDetailsDTO {

    private String username;
    private String name;
    private String bio;
    private LocalDate dob;

    public UserDetailsDTO(String username, String name, String bio, LocalDate dob) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.dob = dob;
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}

