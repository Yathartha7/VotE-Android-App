package com.example.votingsystem;

public class candidateAll {
    private String name;
    private String party;
    private String email;
    private int votes;

    public candidateAll() {
        // Default constructor required for Firebase
    }
    public candidateAll(String name, String party,String email, int votes) {
        this.name = name;
        this.party = party;
        this.email = email;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
