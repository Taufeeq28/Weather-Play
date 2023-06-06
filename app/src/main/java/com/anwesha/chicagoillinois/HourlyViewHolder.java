package com.anwesha.chicagoillinois;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HourlyViewHolder extends RecyclerView.ViewHolder{
    public TextView today,time,temp,description;
    public ImageView image;

    public HourlyViewHolder(@NonNull View itemView) {
        super(itemView);
        today=itemView.findViewById(R.id.today);
        time=itemView.findViewById(R.id.Time);
        temp=itemView.findViewById(R.id.TodayTemperature);
        description=itemView.findViewById(R.id.TodayDescription);
         image=itemView.findViewById(R.id.TodayPageImage);
    }
}
