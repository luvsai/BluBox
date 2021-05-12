package com.example.blubox.services_list.To_do;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;

import com.example.blubox.R;



/*

 *Documentation:------------------------------

    *Name: ToDoList.java (Activity)
          uses res/layout/activity_to_do_list.xml (Resource file for layout)


    *Description :-----------
        ->It will display list of activities
        Each activity can have 0 or more tasks assigned to it
        -> User can create a new activity with a title
        - when he clicks the activity card he  is redirted to the tasks activity for that specific activity


 */

public class ToDoList extends AppCompatActivity {

    FloatingActionButton fab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // View parentLayout = findViewById(android.R.id.content);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }
}