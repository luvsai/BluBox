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
        //getSupportActionBar().hide(); //Hiding Action BAr


        /*
            Hardcoding services details and adding them to the services array list
            services 1.TO_DO  list 2.Notes 3.Personal Blog 4.Gallery

        */

        services.add(new Service("Todo List","ToDoList",getImgRes("todo1"),"Create tasks" ,""));
        services.add(new Service("Notes","Notes",getImgRes("notes"),"Create notes " ,""));
        services.add(new Service("Blog","Blog",getImgRes("blog"),"Write Blog" ,""));
        services.add(new Service("Gallery","Gallery",getImgRes("memories"),"Safe" ,""));




        /*
        Linking data to Recycler view UI
         */


        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false) );//for vertical scrolling
        registerForContextMenu(recyclerView);
        myAdapter = new ServiceAdapter(services, MainActivity.this); // creating adapter object with the services data
        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView






    }



    /*
        the following function is called when service card is selected
     */
    @Override
    public void onItemClicked(int index, ArrayList<Service> services) {


        String MainActivityPath = "com.example.blubox.services_list." + services.get(index).getService_Activity() ;
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
    Function to get Drawable image resource from the image image name
     */

    public Drawable getImgRes(String name) {

        String uri = "@drawable/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }
}