package com.anwesha.chicagoillinois;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DailyForecastActivity extends AppCompatActivity {

    private static final String TAG = "SeconActivity_DailyWeather";
    private static final ArrayList<Daily> dayWeatherArrayList= new ArrayList<>();


    private RecyclerView dayDataRecycler;
    private DailyAdapter dayWeatherAdapter;

    TextView datetimeDisplay;
    //ArrayList<DayWeather> dayWeatherArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        dayDataRecycler = findViewById(R.id.dailyrecyclerview);

        boolean degree= getIntent().getBooleanExtra("degree" , true);
        String city = getIntent().getStringExtra("city");
setTitle(city+" 15 Day");


        doDownload(degree , city);



    }


    public void doDownload(boolean degree , String city){

        DataLoaderVolley.DaydownloadWeather(this ,city , degree);
    }


    public String returnTemperature(JSONObject dayJsonObj , int pos){

        for (int i = 0; i < dayJsonObj.length(); i++) {
            try {
                JSONArray hourlyJsonArray  = dayJsonObj.getJSONArray("hours");

                JSONObject hourlyJsonObj = hourlyJsonArray.getJSONObject(pos);

                return hourlyJsonObj.getString("temp");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



    public void updateData(Weather weather) {

        boolean degree= getIntent().getBooleanExtra("degree" , true);

        if (weather == null) {
            Toast.makeText(this, "Enter a Valid City Name", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            dayWeatherArrayList.clear();
            // Getting the ArrayList with JSON at first
            ArrayList<JSONObject> weatherDayArrayList = weather.getDays();
            for (int i = 0; i < weatherDayArrayList.size(); i++) {
                JSONObject dayJsonObject = weatherDayArrayList.get(i);



                String description = dayJsonObject.getString("description");
                String tempMax = dayJsonObject.getString("tempmax");
                String tempMin = dayJsonObject.getString("tempmin");
                String precipprob = dayJsonObject.getString("precipprob");
                String uvIndex = dayJsonObject.getString("uvindex");
                String icon = dayJsonObject.getString("icon");

                // Converting DayTimeEpoch to Viewable string
                long dateTimeLong = Long.parseLong(dayJsonObject.getString("datetimeEpoch"));
                Date dateTime = new Date(dateTimeLong * 1000);
                SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());

                String datetimeEpoch =  dayDate.format(dateTime);
                Log.d(TAG , icon);
String s="";
if(degree)
    s="°F";
else
   s= "°C";
                String txtHighLow = tempMax +s + "/" + tempMin + s;

                // Temp for different day
                String morningTemp = ( returnTemperature(dayJsonObject, 8)+s);
                String noonTemp = (returnTemperature(dayJsonObject, 13)+s);
                String eveTemp = (returnTemperature(dayJsonObject, 17)+s);
                String nightTemp = ( returnTemperature(dayJsonObject, 23)+s);









                dayWeatherArrayList.add(new Daily(txtHighLow ,  description, datetimeEpoch,
                        precipprob, uvIndex, icon,
                        morningTemp, noonTemp, eveTemp, nightTemp));

            }
            DailyAdapter dailyAdapter = new DailyAdapter(dayWeatherArrayList , DailyForecastActivity.this);
            dayDataRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            dailyAdapter.notifyDataSetChanged();
            dayDataRecycler.setAdapter(dailyAdapter);

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }





    //TextView addressId = findViewById(R.id.address);
    //TextView currentCloudCover = findViewById(R.id.currentDesc);
    // TextView currentDateTimeEpochId = findViewById(R.id.currentDateTimeEpoch);

}






/*import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyForecastActivity extends AppCompatActivity {
    private static final ArrayList<Daily> dailyList = new ArrayList<>();  // Main content is here
    private static final String TAG = "DailyForecastActivity";
    private RecyclerView recyclerView; // Layout's recyclerview
    private DailyAdapter mAdapter; // Data to recyclerview adapter
    TextView DayandDate;
    boolean metric;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        recyclerView = findViewById(R.id.dailyrecyclerview);
        mAdapter = new DailyAdapter(dailyList, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Load the data
        Log.d(TAG,mAdapter+"");
        String city = getIntent().getStringExtra("city");
        Log.d(TAG,"dailycity"+city);
       boolean f=getIntent().getBooleanExtra("deg",true);

        DataLoaderVolley.getSourceData(this,city,f);
        setTitle(city+" 15 Day");

    }

    public void downloadFailed() {
        //dailyList.add(new Daily("1","1","1","1","1","1","1","1","1","1","1"));
        Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show();
    }

    public void updateData(Weather list) {
        boolean degree= getIntent().getBooleanExtra("deg" , true);

        if (list== null) {
            Toast.makeText(this, "Please Enter a Valid City Name", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<JSONObject>w= list.getDays();
        for(int i=0;i<list.getDays().size();i++) {
            JSONObject ob = w.get(i);
            try{
                long datetimeEpoch=ob.getLong("datetimeEpoch");
                Date dateTime = new Date(datetimeEpoch * 1000); // Java time values need milliseconds
                SimpleDateFormat fullDate =
                        new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
                SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());
                SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());
                String fullDateStr = fullDate.format(dateTime); // Thu Sep 29 12:00 AM, 2022
                String timeOnlyStr = timeOnly.format(dateTime); // 12:00 AM
                String dayDateStr = dayDate.format(dateTime); // Thursday 09/29

                String max=ob.getString("tempmax");
                String min=ob.getString("tempmin");
                String pp=ob.getString("precipprob");
                String uvind=ob.getString("uvindex");
                String des=ob.getString("description");
                String icon=ob.getString("icon");
                icon = icon.replace("-", "_"); // Replace all dashes with underscores
                int iconID =
                        this.getResources().getIdentifier(icon, "drawable", this.getPackageName());
                if (iconID == 0) {
                    Log.d(TAG, "parseCurrentRecord: CANNOT FIND ICON " + icon);
                }
                String u="";
                if(degree==true)
                    u="F";
                else
                    u="C";

                JSONArray hr=ob.getJSONArray("hours");
                String mt=hr.getJSONObject(8).getString("temp")+u;
                String at=hr.getJSONObject(13).getString("temp")+u;
                String et=hr.getJSONObject(17).getString("temp")+u;
                String nt=hr.getJSONObject(23).getString("temp")+u;
                //String dateTimeEpoch=ob.getString("dateTimeEpoch");

   dailyList.add(new Daily(dayDateStr+"",max+u,min+u,pp, uvind,des,mt,at,et,nt,iconID+""));

        } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter.notifyDataSetChanged();

    }
}
*/