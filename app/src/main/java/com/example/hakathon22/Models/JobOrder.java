package com.example.hakathon22.Models;

public class JobOrder {
    private   String jobName;
    private  String companyName;
    private String status;
    private String areaName;

    public JobOrder(String jobName, String companyName, String status, String areaName) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.status = status;
        this.areaName = areaName;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}