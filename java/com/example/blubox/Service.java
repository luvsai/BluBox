package com.example.blubox;

/*
*Class to store the data for a Service object
* contains getter and setter methods to access the data
* created in intention to use in the recycler view


 */

import android.graphics.drawable.Drawable;

public class Service {
    private  int id ;
    private String service_Name, service_Activity, service_Msg,  time,imguri;
    private Drawable img ;

    public Service( int id ,String service_Name ,String service_Activity,Drawable img , String service_Msg,String time,String imguri) {
        this.id = id; //it is used by Service Adapter class to differentiate layouts
        this.service_Name  = service_Name ; //storing the name of the service
        this.service_Activity  = service_Activity ; //storing path tto the service Activity class
        this.img = img; //image location url
        this.service_Msg = service_Msg; //service Desciption
        this.time = time; //last accessed time
        this.imguri = imguri ;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//Name of the Service

    public String getService_Name() {
        return service_Name;
    }

    public void setService_Name(String service_Name) {
        this.service_Name = service_Name;
    }

    public String getService_Activity() {
        return service_Activity;
    }

    public void setService_Activity(String service_Activity) {
        this.service_Activity = service_Activity;
    }

    //Description of the Service

    public String getService_Msg() {
        return service_Msg;
    }

    public void setService_Msg(String service_Msg) {
        this.service_Msg = service_Msg;
    }

    //Image location for the Service

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }


    //Service last Accessed Time

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    //Image Uri for User profile pic
    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }
}
