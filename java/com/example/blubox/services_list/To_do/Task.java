package com.example.blubox.services_list.To_do;

public class Task {


    private String  tTitle, tTStamp, temp;
    private int tId,tQid, tStatus;



    public Task(int tId,int tQid,String tTitle, String tTStamp,int tStatus,String temp ) {
        this.tId =tId;
        this.tQid = tQid ;
        this.tTitle =  tTitle;
        this.tTStamp = tTStamp;
        this.temp = temp;
        this.tStatus =tStatus ;

    }


    /*
        *Getter and setter methods for the class attributes

     */

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int gettQId() {
        return tQid;
    }

    public void settQId(int tQId) {
        this.tQid = tQId;
    }

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String gettTStamp() {
        return tTStamp;
    }

    public void settTStamp(String tTStamp) {
        this.tTStamp = tTStamp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int gettStatus() {
        return tStatus;
    }

    public void settStatus(int tStatus) {
        this.tStatus = tStatus;
    }
}
