package com.example.blubox.services_list.To_do;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Quest {



    private String qTitle, qImg , qTStamp, qMsg ;
    private int qId , qTaskCount ,qReached ,qStatus;



    public Quest(int qId ,String qTitle, String qMsg,String qImg ,String qTStamp,int qTaskCount , int qReached,int qStatus ) {
        this.qId= qId;
        this.qTitle =  qTitle;
        this.qMsg = qMsg;
        this.qImg = qImg;
        this.qTStamp = qTStamp;
        this.qTaskCount = qTaskCount;
        this.qReached = qReached ;
        this.qStatus = qStatus ;

    }

    /*
        * Getter and Setter methods to access data

     */
    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqImg() {
        return qImg;
    }

    public void setqImg(String qImg) {
        this.qImg = qImg;
    }

    public String getqTStamp() {
        return qTStamp;
    }

    public void setqTStamp(String qTStamp) {
        this.qTStamp = qTStamp;
    }

    public String getqMsg() {
        return qMsg;
    }

    public void setqMsg(String qMsg) {
        this.qMsg = qMsg;
    }

    public int getqTaskCount() {
        return qTaskCount;
    }

    public void setqTaskCount(int qTaskCount) {
        this.qTaskCount = qTaskCount;
    }

    public int getqReached() {
        return qReached;
    }

    public void setqReached(int qReached) {
        this.qReached = qReached;
    }

    public int getqStatus() {
        return qStatus;
    }

    public void setqStatus(int qStatus) {
        this.qStatus = qStatus;
    }
}
