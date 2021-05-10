package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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




  * --------------------------------------------

 */



public class MainActivity extends AppCompatActivity implements ServiceAdapter.ItemClicked {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutmanager;

    ArrayList<Service> services ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //-------------

    @Override
    public void onItemClicked(int index, ArrayList<Service> services) {

    }

    @Override
    public void onImgClicked(int index, ArrayList<Service> services) {

    }
}