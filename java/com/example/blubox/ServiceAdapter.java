package com.example.blubox;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    ItemClicked context;


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


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName , tvMsg, tvTime;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName  =  itemView.findViewById(R.id.tvName);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            tvTime  = itemView.findViewById(R.id.tvTime);
            img = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onItemClicked(services.indexOf((Service) view.getTag()),services);
                }
            });



        }
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_template, viewGroup, false );
        return new ViewHolder(v);
    }




    /*
    The following function is called for every item in services arraylist

     */


    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(services.get(i));
        viewHolder.tvName.setText(services.get(i).getService_Name());
        viewHolder.tvMsg.setText(services.get(i).getService_Msg());
        viewHolder.tvTime.setText(services.get(i).getTime());
        viewHolder.img.setImageDrawable(services.get(i).getImg());






    }


    /*
    return the length of the list (services)
     */
    @Override
    public int getItemCount() {
        return services.size();
    }










}
