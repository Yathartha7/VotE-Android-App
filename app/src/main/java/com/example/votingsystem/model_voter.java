package com.example.votingsystem;

public class model_voter {
        private String name;
        private int age;
        private String gender;
        private String email;
        private String aadhar;
        private String address;
        private boolean hasVoted;

        public model_voter() {
            // Default constructor required for Firebase
        }

        public model_voter(String name, int age, String gender, String address, String aadhar, String email, boolean hasVoted) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.aadhar = aadhar;
            this.address = address;
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

    public String getAadhar() {
        return aadhar;
    }

    public void setAadharNumber(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}

