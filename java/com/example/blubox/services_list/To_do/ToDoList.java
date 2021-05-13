package com.example.blubox.services_list.To_do;

import android.os.Bundle;

import com.example.blubox.MainActivity;
import com.example.blubox.ServiceAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.blubox.R;

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: ToDoList.java (Activity)
          uses res/layout/activity_to_do_list.xml (Resource file for layout)


    *Description :-----------
        ->It will display list of Quests (activities)
        Each Quest can have 0 or more tasks assigned to it
        -> User can create a new Quest with a title. or delete old ones
        - when he clicks the activity card he  is redirted to the tasks activity for that specific activity


 */

public class ToDoList extends AppCompatActivity implements  QuestAdapter.QuestClicked {

    ArrayList<Quest> quests ;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = findViewById(R.id.toolbar);

        getSupportActionBar().hide(); //Hiding Action BAr
        setSupportActionBar(toolbar);



        quests = new ArrayList<Quest>();



        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        //for vertical scrolling
        recyclerView.setLayoutManager(new LinearLayoutManager(ToDoList.this, LinearLayoutManager.VERTICAL, true) );
        registerForContextMenu(recyclerView);



        // creating adapter object with the services data
        myAdapter = new QuestAdapter(quests, ToDoList.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView


        //Call refresh layout
        refreshLayout() ;


    }




    void refreshLayout() {



        // creating adapter object with the services data
        myAdapter = new QuestAdapter(quests, ToDoList.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView




    }


    @Override
    public void onQuestCreate(int index, ArrayList<Quest> quests) {


    }

    @Override
    public void onQuestEdit(int index, ArrayList<Quest> quests) {

    }

    @Override
    public void onQuestDelete(int index, ArrayList<Quest> quests) {

    }
}





//--------------------
/*

         // View parentLayout = findViewById(android.R.id.content);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

 */