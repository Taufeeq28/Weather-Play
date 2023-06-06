package com.anwesha.chicagoillinois;

import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.List;

class HourlyAdapter extends RecyclerView.Adapter<HourlyViewHolder> {
    private static final String TAG = "HourlyAdapter";
    private final List<Hourly> hourList;
    private MainActivity mainAct;


    HourlyAdapter(List<Hourly> dailyList, MainActivity ma) {
        this.hourList = dailyList;
        mainAct = ma;
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Log.d(TAG, "onCreateViewHolder: MAKING NEW");

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_view, parent, false);

        return new HourlyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        Hourly country = hourList.get(position);
        holder.today.setText(country.getDay());
        holder.time.setText(country.getTime());
        holder.description.setText(country.getConditions());
        String s="";
        if(mainAct.degree)
            s="°F";
        else
            s= "°C";
        holder.temp.setText(country.getTemp()+s);


        int iconId = returnIcon(country.getIcon());
        if (iconId !=0) {
            holder.image.setImageResource(iconId);
    }

}
    public int getItemCount() {
        return hourList.size();
    }
    private int returnIcon(String iconName) {
        String icon = iconName;
        icon = icon.replace("-", "_"); // Replace all dashes with underscores
        int iconID = mainAct.getResources().getIdentifier(icon, "drawable", mainAct.getPackageName());
        if (iconID == 0) {
            String TAG = "HourlyAdapter";
            Log.d(TAG, "Icon Error: Cannot find icon " + icon);
            return 0;
        }

        return iconID;
    }
}
