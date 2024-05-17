package com.example.votingsystem;

public class voter_profile {
    private String name;
    private int age;
    private String gender;
    private String email; // Add email field
    private boolean hasVoted; // Add votes field
    private String address;
    private String aadhar;

    public voter_profile() {
        // Default constructor required for Firebase
    }

    public voter_profile(String name, int age, String gender, String email, String address, String aadhar, boolean hasVoted) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.aadhar = aadhar;
        this.hasVoted = hasVoted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
}

