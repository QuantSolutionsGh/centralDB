package com.databankgroup.bigdata.model;

public class Table {

    private String milesAccountNumber;

    private String backconnectAccountNumber;

    private String nameInMiles;

    private String nameInBackconnect;

    private String planID;

    private String mobile;

    private String address;

    public String getMilesAccountNumber() {
        return milesAccountNumber;
    }

    public void setMilesAccountNumber(String milesAccountNumber) {
        this.milesAccountNumber = milesAccountNumber;
    }

    public String getBackconnectAccountNumber() {
        return backconnectAccountNumber;
    }

    public void setBackconnectAccountNumber(String backconnectAccountNumber) {
        this.backconnectAccountNumber = backconnectAccountNumber;
    }

    public String getNameInMiles() {
        return nameInMiles;
    }

    public void setNameInMiles(String nameInMiles) {
        this.nameInMiles = nameInMiles;
    }

    public String getNameInBackconnect() {
        return nameInBackconnect;
    }

    public void setNameInBackconnect(String nameInBackconnect) {
        this.nameInBackconnect = nameInBackconnect;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
