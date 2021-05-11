package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: MainActivity.java (Activity)
          uses res/layout/activity_main.xml (Resource file for layout)


    *Description :-----------
        ->This is the Home screen of the application
        ->it displays the list of services provided by the application
        ->It redirects the user to different services page based on the card selection



    *Functionalities(FN):----------

      *Fn1 :
         Displays the list of Services

         *Approach:
            The recycler view is used to display the list of services provided by the app

             ->Service card :
                        an item in a list which has specific design for usage
                -the design for card which displays the service info is taken from : res/layout/services_template.xml
                -the activity_main.xml has recycler view layout which incorporates the service cards dynamically on scrolling,
                    which is performed by the code inside current file .

               ->./Service.java
                    Class which is used to store the attributes of the service


                ->./ServiceAdapter.java
                    adapter class used to link data with UI

         *Fn2 :
            Change to service activity when the service card is clicked

            *Approach :
                The Main Activity implements the funtion  onItemClicked() ; from the interface ServiceAdapter.ItemClicked
                which is called when a service card is selected.

                 onItemClicked() function below will redirect user to reQuired activity class


  * --------------------------------------------

 */



public class MainActivity extends AppCompatActivity implements ServiceAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;
    ArrayList<Service> services ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        services = new ArrayList<Service>() ;
        getSupportActionBar().hide(); //Hiding Action BAr

        /*
            * Adding user name and bio for the  multiple adapter recycler view
            * USer data is stored in shared Preference storage and accessible for entire app
            * Class userbio.java has Functions to retreive the user data
         */
        userbio dat = new userbio(MainActivity.this); //class userbio.java helps to retreive  user data from shared preference storage
        String username, bio, profileUrl ,editprofileurl;
        username = dat.getName() ;
        bio = dat.getBio() ;
        profileUrl = "ic_launcher_round" ; //Profile pic system is not updated yet
        editprofileurl =  "Userdata" ; // Userdata.java - class path for editing the user data



        /*
            * Adding user data to create the user bio template in Home screen
            * id = 0 represents the userdata Service object in the Arraylist services for user_bio_template.xml card.
            * the ServiceAdapter class will

         */

        services.add(new Service(0,username,editprofileurl,getMipmapImgRes(profileUrl),bio ,""));

        /*
            Service class id = 1 for the Services_template.xml card in recycler view

            Hardcoding services details and adding them to the services array list
            services 1.TO_DO  list 2.Notes 3.Personal Blog 4.Gallery


        */

        services.add(new Service(1,"Todo List","services_list.ToDoList",getImgRes("todo1"),"Create Activity and set of  tasks  in it and complete tasks" ,""));
        services.add(new Service(1,"Notes","services_list.Notes",getImgRes("notes"),"Create notes " ,""));
        services.add(new Service(1,"Blog","services_list.Blog",getImgRes("blog"),"Write Blog" ,""));
        services.add(new Service(1,"Gallery","services_list.Gallery",getImgRes("memories"),"Safe" ,""));




        /*
            * Linking data to Recycler view UI
            * Check ServiceAdapter.java class for detailed code to integrate the data with Views in Recycler view

         */


        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        //for vertical scrolling
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false) );
        registerForContextMenu(recyclerView);

        // creating adapter object with the services data
        myAdapter = new ServiceAdapter(services, MainActivity.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView






    }



    /*
        the following function implemented by the current class is called when service card is selected
     */
    @Override
    public void onItemClicked(int index, ArrayList<Service> services) {


        String MainActivityPath = "com.example.blubox." + services.get(index).getService_Activity() ;
        Intent i = null;
        try {
            i = new Intent(MainActivity.this, Class.forName(MainActivityPath));
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Service  " + services.get(index).getService_Name(), Toast.LENGTH_SHORT).show();
            return ;
        }





    }




    /*
    Function to get Drawable image resource from the image name in drawable folder
     */

    public Drawable getImgRes(String name) {

        String uri = "@drawable/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }

    /*
    Function to get Drawable image resource from the image name in mipmap  folder
     */

    public Drawable getMipmapImgRes(String name) {

        String uri = "@mipmap/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }






}