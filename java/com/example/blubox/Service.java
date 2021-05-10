package com.example.blubox;

/*
*Class to store the data for a Service object
* contains getter and setter methods to access the data
* created in intention to use in the recycler view


 */

public class Service {
    private String service_Name, service_Activity,img , service_Msg,  time;


    public Service( String service_Name ,String service_Activity,String img , String service_Msg,String time) {

        this.service_Name  = service_Name ; //storing the name of the service
        this.service_Activity  = service_Activity ; //storing path tto the service Activity class
        this.img = img; //image location url
        this.service_Msg = service_Msg; //service Desciption
        this.time = time; //last accessed time

    }




    //Name of the Service

    public String getService_Name() {
        return service_Name;
    }

    public void setService_Name(String service_Name) {
        this.service_Name = service_Name;
    }

    //Description of the Service

    public String getService_Msg() {
        return service_Msg;
    }

    public void setService_Msg(String service_Msg) {
        this.service_Msg = service_Msg;
    }

    //Image location for the Service

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    //Service last Accessed Time

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
