package com.example.blubox.services_list.To_do;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blubox.R;
import com.example.blubox.Service;

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: ServiceAdapter.java (helper class)
         uses to dynamically link data with the UI view components

         it consists seperate Viewholder classes for various View templates
*/



public class QuestAdapter extends RecyclerView.Adapter {
    QuestClicked context;
    int id;


    //Definining ids for the different layout cards
    private static final int QUEST = 1;
    private static final int INTRO = 0;


    /*
        Interface which connects the Main activity and the Service Adapter (Service Card Click)
        contains functions implemented by MainActivity and function call by methods inside adapter

     */
    public interface QuestClicked {
        void onQuestCreate(int index, ArrayList<Quest> quests) ;
        void onQuestEdit(int index, ArrayList<Quest> quests)   ;
        void onQuestDelete(int index, ArrayList<Quest> quests) ;

    }


    ArrayList<Quest> quests ;

    /*
    Constructor for the ServiceAdapter Class
     */

    public QuestAdapter( ArrayList<Quest> quests  , Context context) {
        this.quests  = quests ;
        this.context = (QuestClicked) context;
    }




    // Determines the appropriate ViewType whether the layout for the recycler item is Userbio card(USER) or service card (SERVICE) return the value
    @Override
    public int getItemViewType(int position) {
        Quest card = (Quest) quests.get(position);

        if (card.getqId() == -1) {
            // If the card is for displaying QUESTINTRO
            return INTRO;
        } else {
            // If card is used for diplaying QUESTS
            return QUEST;
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
                    .inflate(R.layout.quest_intro_template, parent, false);
            return new IntroHolder(view);
        } else if (viewType == QUEST) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quest_card_template, parent, false);
            return new QuestHolder(view);
        }
        return null;
    }
    //------------

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        id = i;
        View v;
        viewHolder.itemView.setTag(quests.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Quest quest= quests.get(i);
        switch (viewHolder.getItemViewType()) {
            case INTRO:
                ((IntroHolder) viewHolder).bind(quest);
                break;
            case QUEST:
                ((QuestHolder) viewHolder).bind(quest);
        }
    }






    /*
        UserBioHolder class which loads the user_bio_template to the recycler view

     */

    private class IntroHolder extends RecyclerView.ViewHolder  {
        View itemView;
        ImageButton createquest;

        IntroHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            createquest = itemView.findViewById(R.id.createquest);



        }

        void bind(Quest quest) {
            itemView.setTag(quest);

            //Function call to take user to edit bio Acivity

            createquest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onQuestCreate(quests.indexOf(quest),quests);
                }
            });
        }


    }





    /*
       ServiceHolder class which loads the service_template.xml to the recycler view
       adds service cards

     */


    private class QuestHolder extends RecyclerView.ViewHolder  {
        TextView questname , total, done , waytogo;

        ImageButton deletequest, editquest;

        View itemView;
        QuestHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            questname =  itemView.findViewById(R.id.questname);
            total  =  itemView.findViewById(R.id. total);
            done  =  itemView.findViewById(R.id.done);
            waytogo  =  itemView.findViewById(R.id.waytogo);

            deletequest =  itemView.findViewById(R.id.deletequest);
            editquest =  itemView.findViewById(R.id.editquest);


        }

        void bind(Quest quest) {


            itemView.setTag(quest);
            questname.setText(quest.getqTitle());
            total.setText(String.valueOf(quest.getqTaskCount()));
            done.setText(String.valueOf(quest.getqReached()));
            int unfinish  =  quest.getqTaskCount() - quest.getqReached() ;
            waytogo.setText(String.valueOf(unfinish));

            editquest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onQuestEdit(quests.indexOf(quest),quests);
                }
            });

            deletequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onQuestDelete(quests.indexOf(quest),quests);
                }
            });

        }
    }


    /*
    return the length of the list (services)
     */
    @Override
    public int getItemCount() {
        return quests.size();
    }






}
