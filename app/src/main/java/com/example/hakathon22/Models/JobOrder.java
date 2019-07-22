package com.example.hakathon22.Models;

public class JobOrder {
    private   String jobName;
    private  String companyName;
    private String status;
    private String des;

    public JobOrder(String jobName, String companyName,  String des) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.des = des;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}