package com.example.votingsystem;

public class model_candidate_voteNow {
    private String name;
    private int age;
    private String gender;
    private String party;
    private String email;

    public model_candidate_voteNow() {
        // Default constructor required for Firebase
    }

    public model_candidate_voteNow(String name, int age, String gender, String party,String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.party = party;
        this.email = email.replace(".", "_");
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
    public String getParty() {
        return party;
    }
    public void setParty(String party) {
        this.party = party;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email.replace(".", "_");;
    }
}
