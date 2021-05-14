package com.example.blubox.services_list.To_do;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.blubox.R;
import com.example.blubox.SpeedyLinearLayoutManager;
import com.example.blubox.databaseHelpers.dbhelper_blubox;
import com.example.blubox.userbio;

import java.time.LocalDateTime;
import java.util.ArrayList;

/*

 *Documentation:------------------------------

    *Name: TaskActivity.java (Activity)
          uses res/layout/activity_task.xml (Resource file for layout)


    *Description :-----------
        ->It will display list of Tasks
        Each Task has an objective to complete
        the user can mark and umark the task on completion vv


 */





public class TaskActivity extends AppCompatActivity implements  TaskAdapter.TaskClicked {


    dbhelper_blubox myDataBaseHelper ; //DATABASE helper



    final Context context = this;

    ArrayList<Task> tasks ;

    AlertDialog.Builder alertDialogBuilder ;  AlertDialog alertDialog ; //For creating Task Card

    AlertDialog.Builder builder ;  AlertDialog alert ; //For deleting the Task card






    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutmanager;
    RecyclerView.Adapter myAdapter;

    int qId = -1;
    String qTitle ;


    int deleteTaskID = -1 ; //Used by delete Task Functionality to Find which Task to delete



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        getSupportActionBar().hide(); //Hiding Action BAr

        setContentView(R.layout.activity_task);

        try {

            //Getting Quest Id from the intent data
            qId = Integer.parseInt(getIntent().getStringExtra("qId"));


            //Getting Quest Title from the intent data
            qTitle = getIntent().getStringExtra("qTitle");

        }catch (Exception e) {

        }


        /*
        Creating the DataBase helper object to manage the Tasks cards Functionality

         */

        myDataBaseHelper = new dbhelper_blubox(this) ;



        tasks = new ArrayList<Task>(); //creating tasks object



        // calling : setCreateQuestAllert() ;  to Configure the Create Task alert Box
        setCreateTaskAllert() ;

        // calling : setDeleteAllert()  to Configure the Delete Task alert Box
        setDeleteAllert() ;










        /*

        Code for recycler view for Task Activity Display

         */

        recyclerView = findViewById(R.id.Tlist); //in content_to_do_list.xml
        recyclerView.setHasFixedSize(true);

        //for vertical scrolling
        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(TaskActivity.this, SpeedyLinearLayoutManager.VERTICAL, false) );
        registerForContextMenu(recyclerView);



        // creating adapter object with the services data
        myAdapter = new TaskAdapter(tasks, TaskActivity.this);

        recyclerView.setAdapter(myAdapter); //sending the adapter to recyclerView


        //Call refresh layout which will create the user interface with data
        refreshLayout() ;


    }




    /*
        Function to refresh layout after every new Action Performed by user

     */



    void refreshLayout() {

        tasks.clear(); //clearing the list

        userbio dat = new userbio(TaskActivity.this);
        String profileUrl = dat.getPhoto() ; //Profile pic Uri


        qTitle = myDataBaseHelper.getQuestsTitle(qId);

        //Calculate the percent of tasks completed

        int taskcount , taskreached ;

        taskcount = myDataBaseHelper.getTasksCount(qId) ;
        taskreached = myDataBaseHelper.getTasksReachedCount(qId) ;
        int percent = (taskcount - taskreached) / taskcount ;

        //


        //Add the Quest Intro Card data  Displaying the Quest Activity info card at top
        tasks.add(new Task(-1,qId,qTitle,"",percent,profileUrl));


        //Get the data from the Database and append it to the Arrya list
        tasks.addAll(myDataBaseHelper.getTasksData(qId));


        // creating adapter object with the services data
        myAdapter = new TaskAdapter(tasks, TaskActivity.this);

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

    public void setCreateTaskAllert() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText tTitle = (EditText) promptsView
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
                                myDataBaseHelper.insertTaskData(qId,current.toString(),tTitle.getText().toString(),0,"");

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


                                myDataBaseHelper.deleteTask(deleteTaskID) ;

                                //Unseting global QuestdeleteId
                                deleteTaskID = -1 ;
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

     *Functions implementation of the Task adapter Class


     */



    @Override
    public void onTaskCreate(int index, ArrayList<Task> tasks) {

        // create alert dialog for Quest create
        alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        //Refresh layout
        refreshLayout();
        onResume();

    }

    @Override
    public void onTaskEdit(int index, ArrayList<Task> tasks) {

    }

    @Override
    public void onTaskDelete(int index, ArrayList<Task> tasks) {

        //Setting the delete TaskID id
        deleteTaskID = tasks.get(index).gettId() ;


        alert = builder.create();
        alert.show();

        //Refresh layout
        refreshLayout();


    }

    @Override
    public void onTaskClicked(int index, ArrayList<Task> tasks) {

    }
}



