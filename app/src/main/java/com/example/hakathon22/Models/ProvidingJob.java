package com.example.hakathon22.Models;

public class ProvidingJob {
    private String name, age, email, gender, isCompany, type, token;
    public ProvidingJob(String token) {
        this.token = token;
    }

    public ProvidingJob(String name, String age, String email, String gender, String isCompany, String type, String token) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.isCompany = isCompany;
        this.type = type;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(String isCompany) {
        this.isCompany = isCompany;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
