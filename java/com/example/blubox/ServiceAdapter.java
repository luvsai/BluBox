package com.example.blubox;

import android.content.Context;
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

    ArrayList<Service> services;
    public interface ItemClicked {
        void onItemClicked(int index, ArrayList<Service> services) ;
        void onImgClicked(int index, ArrayList<Service> services) ;

    }
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
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.onImgClicked(services.indexOf((Service) view.getTag()),services);
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

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(services.get(i));
        viewHolder.tvName.setText(services.get(i).getService_Name());
        viewHolder.tvMsg.setText(services.get(i).getService_Msg());
        viewHolder.tvTime.setText(services.get(i).getTime());
        if ( services.get(i).getImg().equals("logo")) {
            viewHolder.img.setImageResource(R.drawable.logo);
        }
        else if ( services.get(i).getImg().equals("")){}else {
            String url = services.get(i).getImg();



        }






    }

    @Override
    public int getItemCount() {
        return services.size();
    }










}
