package com.anwesha.chicagoillinois;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyViewHolder> {

    private final List<Daily> dailyList;
    private final DailyForecastActivity mainAct;

    DailyAdapter(List<Daily>dailyList, DailyForecastActivity ma) {
        this.dailyList = dailyList;
        mainAct = ma;
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // Log.d(TAG, "onCreateViewHolder: MAKING NEW");

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_view, parent, false);

        return new DailyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        Daily country = dailyList.get(position);
        //String s=country.getTempmax()+"/"+country.getTempmin();
        holder.DayandDate.setText(country.getDatetimeEpoch());
        holder.Description.setText(country.getDescription());
        holder.ProbOfPrecipitation.setText("("+country.getPrecipprob()+"% precip.)");
        holder.UVindex.setText("UV Index: "+country.getUvindex());
        holder.HighLowTemp.setText(country.getTemp());
        holder.MorningTemp.setText(country.getMorningTemp());
        holder.AfternoonTemp.setText(country.getAfternoonTemp());
        holder.NightTemp.setText(country.getNightTemp());
        holder.EveningTemp.setText(country.getEveningTemp());
       // holder.WeatherIcon.setImageResource(Integer.parseInt(country.getIcon()));
//        holder.MorningTemp.setText("2");
//        holder.AfternoonTemp.setText("1");
//        holder.EveningTemp.setText("3");
//        holder.NightTemp.setText("4");
        int iconID = returnIcon(country.getIcon());
        if (iconID != 0) {
            holder.WeatherIcon.setImageResource(iconID);
        }

    }

    public int getItemCount() {
        return dailyList.size();
    }
    private int returnIcon(String iconName) {
        String icon = iconName;
        icon = icon.replace("-", "_"); // Replace all dashes with underscores
        int iconID = mainAct.getResources().getIdentifier(icon, "drawable", mainAct.getPackageName());
        if (iconID == 0) {
            String TAG = "DayWeatherAdapter";
            Log.d(TAG, "Icon Error: CANNOT FIND ICON " + icon);
            return 0;
        }

        return iconID;
    }
}
