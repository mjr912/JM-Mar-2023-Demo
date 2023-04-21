package com.example.employeeregistration;




import java.sql.Date;

public class EmployeeDetailsData {
    private int id,ctc,experience;
    private String name,gender,permanentAddress,contactAddress,email,mobileNumber,employeeType;
    private Date date;

    public EmployeeDetailsData(int id,String name,String gender,String permanentAddress,String contactAddress,String mobileNumber,Date date,int ctc,int experience,String employeeType,String email){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.permanentAddress=permanentAddress;
        this.contactAddress=contactAddress;
        this.mobileNumber=mobileNumber;
        this.date=date;
        this.ctc=ctc;
        this.experience=experience;
        this.employeeType=employeeType;
        this.email=email;

    }

    public void setId(int userId) {
        this.id = userId;
    }

    public int getId(){
        return id;
    }

    public void setCtc(int ctc) {
        this.ctc = ctc;
    }

    public int getCtc() {
        return ctc;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}

