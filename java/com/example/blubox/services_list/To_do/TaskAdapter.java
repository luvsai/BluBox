package com.example.blubox.services_list.To_do;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blubox.R;

import java.util.ArrayList;


/*

 *Documentation:------------------------------

    *Name: TaskAdapter.java (helper class)
         uses to dynamically link data with the UI view components

         it consists seperate Viewholder classes for various View templates
*/



public class TaskAdapter extends RecyclerView.Adapter {
    TaskClicked context;
    int id;


    //Definining ids for the different layout cards
    private static final int TASK = 1;
    private static final int INTRO = 0;


    /*
        Interface which connects the Main activity and the Service Adapter (Service Card Click)
        contains functions implemented by MainActivity and function call by methods inside adapter

     */
    public interface TaskClicked {
        void onTaskCreate(int index, ArrayList<Task> tasks) ;
        void onTaskEdit(int index, ArrayList<Task> tasks)   ;
        void onTaskDelete(int index, ArrayList<Task> tasks) ;
        void onTaskClicked(int index, ArrayList<Task> tasks) ;

    }


    ArrayList<Task> tasks ;

    /*
    Constructor for the ServiceAdapter Class
     */

    public TaskAdapter( ArrayList<Task> tasks  , Context context) {
        this.tasks = tasks ;
        this.context = (TaskClicked) context;
    }




    // Determines the appropriate ViewType whether the layout for the recycler item is Userbio card(USER) or service card (SERVICE) return the value
    @Override
    public int getItemViewType(int position) {
        Task card = (Task) tasks.get(position);

        if (card.gettId() == -1) {
            // If the card is for displaying TaskINTRO
            return INTRO;
        } else {
            // If card is used for diplaying Tasks
            return TASK;
        }
    }




    /*
        The following fucntion is called once when the adapter object is created

     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == INTRO) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_intro, parent, false);
            return new IntroHolder(view);
        } else if (viewType == TASK) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_card_template, parent, false);
            return new TaskHolder(view);
        }
        return null;
    }
    //------------

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        id = i;
        View v;
        viewHolder.itemView.setTag(tasks.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Task task = tasks.get(i);
        switch (viewHolder.getItemViewType()) {
            case INTRO:
                ((IntroHolder) viewHolder).bind(task);
                break;
            case TASK:
                ((TaskHolder) viewHolder).bind(task);
        }
    }






    /*
        UserBioHolder class which loads the user_bio_template to the recycler view

     */

    private class IntroHolder extends RecyclerView.ViewHolder  {
        View itemView;
        ImageButton createtask;
        TextView ttitle , uname;
        ImageView tprofilepic;
        ProgressBar taskprogress;

        IntroHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            createtask = itemView.findViewById(R.id.creatask);
            tprofilepic = itemView.findViewById(R.id.tprofilepic);
            taskprogress =  (ProgressBar) itemView.findViewById(R.id.progressBar) ;
            ttitle = itemView.findViewById(R.id.tquestitle);
            uname = itemView.findViewById(R.id.uname);
        }

        void bind(Task task) {
            itemView.setTag(task);

            taskprogress.setProgress(task.gettStatus());

            ttitle.setText(task.gettTitle() + " ("+String.valueOf(task.gettStatus())+ ")" );

            String temp ;
            temp = uname.getText().toString() ;
            uname.setText(temp + task.gettTStamp()); //username to the intro card


            if(task.getTemp().isEmpty()) {

            }else {
                tprofilepic.setImageURI(Uri.parse(task.getTemp()));
            }


            //Function call to create a new quest

            createtask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onTaskCreate(tasks.indexOf(task),tasks);
                }
            });
        }


    }





    /*
       ServiceHolder class which loads the service_template.xml to the recycler view
       adds service cards

     */


    private class TaskHolder extends RecyclerView.ViewHolder  {
        TextView taskname ;
        ImageView taskstat ;


        ImageButton deletetask, edittask;

        View itemView;
        TaskHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            taskname =  itemView.findViewById(R.id.taskname);
            taskstat =  itemView.findViewById(R.id.taskStat);

            deletetask =  itemView.findViewById(R.id.deletetask);
            edittask =  itemView.findViewById(R.id.renametask);


        }

        @SuppressLint("ResourceAsColor")
        void bind(Task task) {


            itemView.setTag(task);
            taskname.setText(task.gettTitle());


            int status = task.gettStatus();
            if (status == 0) {
                taskstat.setBackgroundResource(R.color.ofline);
            } else {
                taskstat.setBackgroundResource(R.color.online);
            }

            edittask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onTaskEdit(tasks.indexOf(task),tasks);
                }
            });

            deletetask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onTaskDelete(tasks.indexOf(task),tasks);
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        context.onTaskClicked(tasks.indexOf(task),tasks);
                }
            });


        }
    }


    /*
    return the length of the list (services)
     */
    @Override
    public int getItemCount() {
        return tasks.size();
    }






}
