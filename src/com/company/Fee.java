package com.company;

public class Fee {
    private String name,rollno,course,feeType;
    private int fee;
    private String trimester;


    public Fee() {}

    public Fee(String rollno, String name, String feeType, String course, int fee, String trimester) {
        super();
        this.rollno = rollno;
        this.name = name;
        this.feeType = feeType;
        this.course = course;
        this.fee = fee;
        this.trimester = trimester;
    }

    public String getRollno() {
        return rollno;
    }
    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public String getTrimester() {
        return trimester;
    }
    public void setTrimester(String trimester) {
        this.trimester = trimester;
    }

}
