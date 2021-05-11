package com.example.blubox;

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

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: ServiceAdapter.java (helper class)
         uses to dynamically link data with the UI view components

         it consists seperate Viewholder classes for various View templates
*/



public class ServiceAdapter extends RecyclerView.Adapter {
    ItemClicked context;
    int id;


    //Definining ids for the different layout cards
    private static final int SERVICE = 1;
    private static final int USER = 0;


    /*
        Interface which connects the Main activity and the Service Adapter (Service Card Click)
        contains functions implemented by MainActivity and function call by methods inside adapter

     */
    public interface ItemClicked {
        void onItemClicked(int index, ArrayList<Service> services) ;

    }


    ArrayList<Service> services;

    /*
    Constructor for the ServiceAdapter Class
     */

    public ServiceAdapter(ArrayList<Service> services , Context context) {
        this.services = services;
        this.context = (ItemClicked) context;
    }




    // Determines the appropriate ViewType whether the layout for the recycler item is Userbio card(USER) or service card (SERVICE) return the value
    @Override
    public int getItemViewType(int position) {
        Service card = (Service) services.get(position);

        if (card.getId() == 0) {
            // If the current user is the sender of the message
            return USER;
        } else {
            // If some other user sent the message
            return SERVICE;
        }
    }




    /*
        The following fucntion is called once when the adapter object is created

     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == USER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_bio_template, parent, false);
            return new UserBioHolder(view);
        } else if (viewType == SERVICE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.services_template, parent, false);
            return new ServiceHolder(view);
        }
        return null;
    }
    //------------

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        id = i;
        View v;
        viewHolder.itemView.setTag(services.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Service serve = services.get(i);
        switch (viewHolder.getItemViewType()) {
            case USER:
                ((UserBioHolder) viewHolder).bind(serve);
                break;
            case SERVICE:
                ((ServiceHolder) viewHolder).bind(serve);
        }
    }






    /*
        UserBioHolder class which loads the user_bio_template to the recycler view

     */

    private class UserBioHolder extends RecyclerView.ViewHolder  {
        TextView username , userbio ;
        ImageView profilepic;
        View itemView;
        ImageButton editbio;

        UserBioHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            profilepic = itemView.findViewById(R.id.profilepic);
            editbio = itemView.findViewById(R.id.editbio);
            username =  itemView.findViewById(R.id.username);
            userbio = itemView.findViewById(R.id.userbio);


        }

        void bind(Service serve) {
            itemView.setTag(serve);
            username.setText(serve.getService_Name());
            userbio.setText(serve.getService_Msg());
            if(serve.getImguri().isEmpty()) {
                profilepic.setImageDrawable(serve.getImg());
            }else {
                profilepic.setImageURI(Uri.parse(serve.getImguri()));
            }

            //Function call to take user to edit bio Acivity

            editbio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(services.indexOf(serve),services);
                }
            });
        }


    }





    /*
       ServiceHolder class which loads the service_template.xml to the recycler view
       adds service cards

     */


    private class ServiceHolder extends RecyclerView.ViewHolder  {
        TextView tvName , tvMsg, tvTime;
        ImageView img;
        View itemView;
        ServiceHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName  =  itemView.findViewById(R.id.tvName);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            tvTime  = itemView.findViewById(R.id.tvTime);
            img = itemView.findViewById(R.id.img);
        }

        void bind(Service serve) {


            itemView.setTag(serve);
            tvName.setText(serve.getService_Name());
            tvMsg.setText(serve.getService_Msg());
            tvTime.setText(serve.getTime());
            img.setImageDrawable(serve.getImg());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(services.indexOf(serve),services);
                }
            });



        }

    }


    /*
    return the length of the list (services)
     */
    @Override
    public int getItemCount() {
        return services.size();
    }






}
