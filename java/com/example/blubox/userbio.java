package com.example.blubox;

import android.content.Context;
import android.content.SharedPreferences;



/*

 *Documentation:------------------------------

    *Name: userbio.java (helper class)


    *Description :-----------
        ->Shared Preference class
        ->it has save method to save the User data in shared preference
        ->It has required methods to send data which is read from shared prefereces memory.



*/

public class userbio {
    String Phone = "UserDetails";
    Context context;
    public userbio(Context context) {

        this.context = context;

    }

    /*
    save function to update the user details using shared preferences (username, profile pic and bio)

     */

    public void save(String status ,String name, String bio, String photo) {

        SharedPreferences sharedPref = context.getSharedPreferences(Phone,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("status",status);
        editor.putString("name", name);
        editor.putString("bio", bio);
        editor.putString("photo", photo);

        editor.commit();
    }


    /*
       * Save image url
     */

    public void savePhoto(String photo) {

        SharedPreferences sharedPref = context.getSharedPreferences(Phone,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();


        editor.putString("photo", photo);

        editor.commit();
    }



    /*
    function to retrieve the Status  of user details used when first installed the app to check whether the user details are updated
     */
    public String getStatus() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "false";

            String status= sharedPref.getString("status", defaultValue);
            return status;



        }catch(Exception e){
            e.printStackTrace();
            return "false";
        }


    }





    /*
    function to retrieve the Name of user
     */
    public String getName() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "NO user Name";

            String name = sharedPref.getString("name", defaultValue);
            return name;



        }catch(Exception e){
            e.printStackTrace();
            return "";
        }


    }





    /*
    function to retrieve the Bio of user
     */

    public String getBio() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "No Bio";

            String name = sharedPref.getString("bio", defaultValue);
            return name;



        }catch(Exception e){
            e.printStackTrace();
            return "";
        }


    }


    /*
    function to retrieve the Profile pic uri of user
     */



    public String getPhoto() {
        try {


            SharedPreferences sharedPref = context.getSharedPreferences(Phone, Context.MODE_PRIVATE);

            String defaultValue = "logo";

            String name = sharedPref.getString("photo", defaultValue);
            return name;



        }catch(Exception e){
            e.printStackTrace();
            return "";
        }


    }



}

