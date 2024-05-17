package com.example.votingsystem;

public class candidate_profile {
    private String name;
    private int age;
    private String gender;
    private String email; // Add email field
    private int votes; // Add votes field
    private String party;

    public candidate_profile() {
        // Default constructor required for Firebase
    }

    public candidate_profile(String name, int age, String gender, String email, int votes, String party) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.votes = votes;
        this.party = party;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
// Add getters and setters for email and votes

    // Getters and setters for name, age, gender, and party remain the same
}

