package com.anwesha.chicagoillinois;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.anwesha.chicagoillinois.R;
public class DailyViewHolder extends RecyclerView.ViewHolder {
    public TextView DayandDate;
    public TextView Description;
    public TextView ProbOfPrecipitation;
    public TextView UVindex;
    public TextView HighLowTemp;
    public TextView MorningTemp;
    public TextView AfternoonTemp;
    public TextView EveningTemp;
    public TextView NightTemp;
    public ImageView WeatherIcon;
    public TextView textView8;

    public TextView textView9;
    public TextView textView11;
    public TextView textView13;
    DailyViewHolder(@NonNull View v) {
   super(v);
DayandDate=v.findViewById(R.id.DayAndDate);
Description=v.findViewById(R.id.Description);
ProbOfPrecipitation=v.findViewById(R.id.ProbOfPrecipitation);
UVindex=v.findViewById(R.id.UVindex);
HighLowTemp=v.findViewById(R.id.HighLowTemp);
MorningTemp=v.findViewById(R.id.MorningTemp);
AfternoonTemp=v.findViewById(R.id.AfternoonTemp);
EveningTemp=v.findViewById(R.id.EveningTemp);
NightTemp=v.findViewById(R.id.NightTemp);
WeatherIcon=v.findViewById(R.id.WeatherIcon);
//textView8=v.findViewById(R.id.textView8);
//textView9=v.findViewById(R.id.textView9);
//        textView11=v.findViewById(R.id.textView11);
//        textView13=v.findViewById(R.id.textView13);


    }
}
