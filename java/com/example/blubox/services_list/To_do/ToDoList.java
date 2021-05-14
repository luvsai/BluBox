package com.example.blubox.services_list.To_do;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.blubox.MainActivity;
import com.example.blubox.ServiceAdapter;
import com.example.blubox.SpeedyLinearLayoutManager;
import com.example.blubox.databaseHelpers.dbhelper_blubox;
import com.example.blubox.userbio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blubox.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    dbhelper_blubox myDataBaseHelper ; //DATABASE helper



    final Context context = this;

    ArrayList<Quest> quests ;

    AlertDialog.Builder alertDialogBuilder ;  AlertDialog alertDialog ; //For creating Quest Card

    AlertDialog.Builder builder ;  AlertDialog alert ; //For deleting the Quest card






    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;


    int deleteQuestID = -1 ; //Used by delete Quest Functionality to Find which Quest to delete



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        getSupportActionBar().hide(); //Hiding Action BAr

        setContentView(R.layout.activity_to_do_list);


        /*
        Creating the DataBase helper object to manage the Quest cards Functionality

         */

        myDataBaseHelper = new dbhelper_blubox(this) ;



        quests = new ArrayList<Quest>(); //creating quests object



        // calling : setCreateQuestAllert() ;  to Configure the Create Quest alert Box
        setCreateQuestAllert() ;

        // calling : setDeleteAllert()  to Configure the Delete Quest alert Box
        setDeleteAllert() ;










        /*

        Code for recycler view for Quest Activity Display

         */

        recyclerView = findViewById(R.id.Qlist); //in content_to_do_list.xml
        recyclerView.setHasFixedSize(true);

        //for vertical scrolling
        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(ToDoList.this, SpeedyLinearLayoutManager.VERTICAL, false) );
        registerForContextMenu(recyclerView);



        // creating adapter object with the services data
        myAdapter = new QuestAdapter(quests, ToDoList.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView


        //Call refresh layout which will create the user interface with data
        refreshLayout() ;


    }




    /*
        Function to refresh layout after every new Action Performed by user

     */



    void refreshLayout() {

        quests.clear(); //clearing the list

        userbio dat = new userbio(ToDoList.this);
        String profileUrl = dat.getPhoto() ; //Profile pic Uri


        //Add the Quest Intro Card data  Displaying the Quest Activity info card at top
        quests.add(new Quest(-1,"","",profileUrl,"",0,0,0)) ;


        //Get the data from the Database and append it to the Arrya list
        quests.addAll(myDataBaseHelper.getQuestsData());


        // creating adapter object with the services data
        myAdapter = new QuestAdapter(quests, ToDoList.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView




    }

    //Refresh on resume
    @Override
    protected void onResume() {
        super.onResume();

        refreshLayout();
    }




    /*

    Alert dialog pop up setup for creating new Quest

     */

    public void setCreateQuestAllert() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText qTitle = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            public void onClick(DialogInterface dialog, int id) {
                                LocalDateTime current = LocalDateTime.now();

                               /* Use this formatter
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                                String formatted = current.format(formatter);

                                System.out.println("Current Date and Time is: " + formatted);


                                */
                                myDataBaseHelper.insertQuestData(qTitle.getText().toString(),"","",current.toString(),0,0,0);
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();


                            }
                        });

        alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener()  {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ((ViewGroup)promptsView.getParent()).removeView(promptsView);
                refreshLayout() ;
            }
        });




    }




/*

    Function to set Quest card Delete Alert dialog box


     */

    public void setDeleteAllert() {


        builder = new AlertDialog.Builder(getApplicationContext());



        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_delete, null); //layout for delete prompt


        builder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder

        builder.setView(promptsView);

        // set dialog message

        builder
                .setCancelable(false)
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            public void onClick(DialogInterface dialog, int id) {


                                myDataBaseHelper.delete(deleteQuestID) ;
                                myDataBaseHelper.deleteTasks(deleteQuestID) ;

                                //Unseting global QuestdeleteId
                                deleteQuestID = -1 ;
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();


                            }
                        });


        builder.setOnDismissListener(new DialogInterface.OnDismissListener()  {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ((ViewGroup)promptsView.getParent()).removeView(promptsView);
                refreshLayout() ;
            }
        });






    }









    /*

            *Functions implementation of the Quest adapter Class


     */

    @Override
    public void onQuestClicked(int index, ArrayList<Quest> quests) {
        int id = quests.get(index).getqId() ;


        /*
        Directs the uer to respectiv activity
         */

        String TaskActivityPath = "com.example.blubox.services_list.To_do.TaskActivity" ;
        Intent i = null;
        try {
            i = new Intent(ToDoList.this, Class.forName(TaskActivityPath));
            i.putExtra("id",String.valueOf(id));
            startActivity(i);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ;
        }

    }

    @Override
    public void onQuestCreate(int index, ArrayList<Quest> quests) {


        // create alert dialog for Quest create
        alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        //Refresh layout
        refreshLayout();
        onResume();

    }

    @Override
    public void onQuestEdit(int index, ArrayList<Quest> quests) {




        //Refresh layout
        refreshLayout();
    }

    @Override
    public void onQuestDelete(int index, ArrayList<Quest> quests) {

        //Setting the delete Quest id
        deleteQuestID = quests.get(index).getqId() ;


        alert = builder.create();
        alert.show();

        //Refresh layout
        refreshLayout();
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