package com.anwesha.chicagoillinois;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static DailyForecastActivity dailyForecastActivity;
    private static Weather weatherO;
    private static final ArrayList<Hourly> hourList = new ArrayList<>();

    private static MainActivity mainActivity;
    public String city = "Chicago, IL";
    private static RecyclerView horizontalRecyclerView;
    SwipeRefreshLayout swiper;
    private ConstraintLayout constraintLayout;
    private LinearLayout linearInternet,linearNoInternet;
    private HourlyAdapter hourlyAdapter;


    private SharedPreferences.Editor editor;


    boolean degree = true;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearInternet  = findViewById(R.id.linearInternet);
        linearNoInternet  = findViewById(R.id.linearNoInternet);
        TextView morningtext=findViewById(R.id.morningtext);
        TextView afternoontext=findViewById(R.id.afternoontext);
        TextView eveningtext=findViewById(R.id.eveningtext);
        TextView nighttext=findViewById(R.id.nighttext);
getData();


        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);
        swiper = findViewById(R.id.swiper);
        swiper.setOnRefreshListener(this::doRefresh);

        if (!hasNetworkConnection()){
            linearInternet.setVisibility(View.GONE);
            linearNoInternet.setVisibility(View.VISIBLE);
            morningtext.setVisibility(View.GONE);
            afternoontext.setVisibility(View.GONE);
            eveningtext.setVisibility(View.GONE);
            nighttext.setVisibility(View.GONE);
            swiper.setRefreshing(false);
            Toast.makeText(this, "No Internet Connection." , Toast.LENGTH_SHORT).show();
        }else {

                DataLoaderVolley.HomedownloadWeather(this , city , degree);

            linearInternet.setVisibility(View.VISIBLE);
            linearNoInternet.setVisibility(View.GONE);
            swiper.setRefreshing(false);
            //Toast.makeText(this, "Network Connected!" , Toast.LENGTH_SHORT).show();
        }



    }



    public void saveData() {
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putBoolean("FARENHEIT", degree);
        editor.putString("location", city);
        editor.apply();
    }

    private void getData() {
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        degree = prefs.getBoolean("FARENHEIT", true);
        city = prefs.getString("location", "Chicago, Illinois");
        setTitle(city);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getData();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        saveData();
        super.onSaveInstanceState(outState);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void doRefresh(){
        TextView morningtext=findViewById(R.id.morningtext);
        TextView afternoontext=findViewById(R.id.afternoontext);
        TextView eveningtext=findViewById(R.id.eveningtext);
        TextView nighttext=findViewById(R.id.nighttext);
        if (!hasNetworkConnection()){

            linearInternet.setVisibility(View.VISIBLE);
            linearNoInternet.setVisibility(View.GONE);
            morningtext.setVisibility(View.GONE);
            afternoontext.setVisibility(View.GONE);
            eveningtext.setVisibility(View.GONE);
            nighttext.setVisibility(View.GONE);
            swiper.setRefreshing(false);
        }else{
            if (hasNetworkConnection()){
                DataLoaderVolley.HomedownloadWeather(this , city , degree);
            }

        }
        swiper.setRefreshing(false);

    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateData(Weather weather) {


        if (weather == null) {
            Toast.makeText(this, "Enter valid City Name", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<JSONObject> weatherDayArrayList= weather.getDays();

        // Just for the Current Day
        JSONObject currentDayJsonObj = weatherDayArrayList.get(0);

        TextView dateTimeEpochId = findViewById(R.id.datetimeEpoch);
        TextView temp = findViewById(R.id.temp);
        TextView feelsLike = findViewById(R.id.feelslike);
        TextView currentCloud = findViewById(R.id.condCloudCoverId);
        TextView  windSpeed = findViewById(R.id.windSpeed);
        TextView humidity = findViewById(R.id.humidity);
        TextView uvIndex = findViewById(R.id.uvIndex);
        TextView description = findViewById(R.id.weatherDescription);
        TextView morningTempId = findViewById(R.id.morningTemp);
        TextView noonTempId = findViewById(R.id.afternTemp);
        TextView eveTempId = findViewById(R.id.EvngTemp);
        TextView nightTempId = findViewById(R.id.ni8Temp);
        TextView visibilityId = findViewById(R.id.Visibility);

        TextView textSunRiseId = findViewById(R.id.sunriseEpoch);
        TextView textSunSetId = findViewById(R.id.sunset);
        ImageView currentWeatherImgId = findViewById(R.id.imageviewhr);

        int iconID = returnIcon(weather.getIcon());
        if (iconID != 0) {
            currentWeatherImgId.setImageResource(iconID);

        }else{
            Log.d(TAG, "Image not found");
        }


        visibilityId.setText("Visibility: " + weather.getVisiblility() + "mi");

        String windParams = weather.getWinddir() + weather.getWindspeed() + weather.getWindgust();


        String windDir = getDirection(Double.parseDouble(weather.getWinddir()));
        windSpeed.setText("Winds: " + windDir + " at " + weather.getWindspeed() + "mph gusting to " + weather.getWindgust() + "mph");

        String condCloud = weather.getConditions() + "(" + weather.getCloudcover() + "% clouds" + ")";

        String morningTemp = returnTemperature(currentDayJsonObj, 8);
        Log.d(TAG, "updateData: "+ morningTemp);
        String noonTemp = returnTemperature(currentDayJsonObj, 13);
        String eveTemp = returnTemperature(currentDayJsonObj, 17);
        String nightTemp = returnTemperature(currentDayJsonObj, 23);
if(hasNetworkConnection())
    dateTimeEpochId.setText((dateTimeConvertor(weather.getDatetimeEpoch() , "fullDate")));
else
    dateTimeEpochId.setText("No internet connection");
        String s="";
        if(degree)
            s="°F";
        else
            s= "°C";
        temp.setText( weather.getTemp()+s);

        uvIndex.setText("UV Index "+weather.getUvindex());
        humidity.setText("Humidity: "+ weather.getHumidity()+"%");

        currentCloud.setText(condCloud);

        feelsLike.setText((String.format("Feels like: %s° %s", weather.getFeelslike(), s)));
        morningTempId.setText( morningTemp+s);
        noonTempId.setText( noonTemp+ s);
        eveTempId.setText(eveTemp+ s);
        nightTempId.setText( nightTemp+ s);



        String sunRiseText = dateTimeConvertor(weather.getSunriseEpoch() , "timeOnly");
        String sunSetText = dateTimeConvertor(weather.getSunsetEpoch() , "timeOnly");

        textSunRiseId.setText("Sunrise: "+ sunRiseText);

        textSunSetId.setText("Sunset: "+ sunSetText);





        long dateTimeEpoch = Long.parseLong(weather.getDatetimeEpoch());
        Date currentDateTimeEpoch = new Date(dateTimeEpoch * 1000);


        SimpleDateFormat fullDate = new SimpleDateFormat("EEE MMM dd h:mm a, yyyy" , Locale.getDefault());
        String fullDateStr = fullDate.format(currentDateTimeEpoch);


        try {
            int accum = 0;
            hourList.clear();
            for (int i = 0; i <=3; i++) {
                JSONArray hourJsonArray = weatherDayArrayList.get(i).getJSONArray("hours");


                for (int j = 0; j < hourJsonArray.length(); j++) {
                    JSONObject eachJsonObj = hourJsonArray.getJSONObject(j);
                    accum+= 1;

                    String hourlyDateTimeEpoch = eachJsonObj.getString("datetimeEpoch");
                    String hourlyIcon = eachJsonObj.getString("icon");
                    String hourlyTemp = eachJsonObj.getString("temp");

                    // Log.d(TAG , "Hourly Temp: " + hourlyTemp);
                    String hourlyCond = eachJsonObj.getString("conditions");

                    long hourlyDateTime = Long.parseLong(hourlyDateTimeEpoch);
                    //Log.d(TAG , hourlyDateTimeEpoch);
                    Date hourlyDate = new Date(hourlyDateTime * 1000);

                    SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a" , Locale.getDefault());
                    timeOnly.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
                    SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd" , Locale.getDefault());

                    String timeonlyStr = timeOnly.format(hourlyDate);

                    String dayDateStr = dayDate.format(hourlyDate);
                    if(i==0)
                        hourList.add(new Hourly("Today" , timeonlyStr , hourlyTemp , hourlyCond,hourlyIcon ));
else
                    hourList.add(new Hourly(dayDateStr , timeonlyStr , hourlyTemp , hourlyCond,hourlyIcon ));

                }
                HourlyAdapter hourlyWeatherAdapter = new HourlyAdapter(hourList, this);
                horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));
                horizontalRecyclerView.setAdapter(hourlyWeatherAdapter);






            }






        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String dateTimeConvertor(String dateTimeEpochStr , String choice){
        /** Gives the Date Time in desired format
         * choice -> Is the type of format we want the output to be
         *
         * fullDate -> Thu Sep 29 12:00 AM, 2022
         * timeOnly -> 12:00 AM
         * dayDate -> Thursday 09/29
         */



        long dateTimeEpoch = Long.parseLong(dateTimeEpochStr);
        Date dateTime = new Date(dateTimeEpoch * 1000);
        if (choice == "fullDate"){
            SimpleDateFormat fullDate =
                    new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());

            return fullDate.format(dateTime);
        }else if(choice == "timeOnly"){
            SimpleDateFormat timeOnly = new SimpleDateFormat("h:mm a", Locale.getDefault());

            return timeOnly.format(dateTime);
        }else if (choice == "dayDate"){

            SimpleDateFormat dayDate = new SimpleDateFormat("EEEE MM/dd", Locale.getDefault());
            return dayDate.format(dateTime);
        }

        return null;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_main, menu);
        MenuItem item = menu.getItem(0);
        if(degree){
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.units_f));
        }
        else{
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.units_c));
        }
        return true;


    }





//    public void checkNetwork(){
//        if(hasNetworkConnection()){
//            linearInternet.setVisibility(View.VISIBLE);
//            linearNoInternet.setVisibility(View.GONE);
//        }
//        else{
//            linearInternet.setVisibility(View.GONE);
//            linearNoInternet.setVisibility(View.VISIBLE);
//        }
//    }

    private boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    private String getDirection(double degrees) {
        if (degrees >= 337.5 || degrees < 22.5)
            return "N";
        if (degrees >= 22.5 && degrees < 67.5)
            return "NE";
        if (degrees >= 67.5 && degrees < 112.5)
            return "E";
        if (degrees >= 112.5 && degrees < 157.5)
            return "SE";
        if (degrees >= 157.5 && degrees < 202.5)
            return "S";
        if (degrees >= 202.5 && degrees < 247.5)
            return "SW";
        if (degrees >= 247.5 && degrees < 292.5)
            return "W";
        if (degrees >= 292.5)
            return "NW";
        return "X"; // We'll use 'X' as the default if we get a bad value
    }

    public void openDailyActivity() {
        Intent intent = new Intent(this, DailyForecastActivity.class);
        startActivity(intent);
    }


    public int returnIcon(String icon){

        icon = icon.replace("-" , "_");
        int iconId = MainActivity.this.getResources().getIdentifier(icon , "drawable" , MainActivity.this.getPackageName());

        // Error Handling
        if (iconId ==0){
            Log.d(TAG , "parseCurrentRecord: CANNOT FIND ICON" + icon);
            return 0;
        }
        return iconId;
    }






    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuB) {
            if (hasNetworkConnection()) {
                Intent intent = new Intent(this, DailyForecastActivity.class);
                intent.putExtra("city", city);
                intent.putExtra("degree", degree);
                startActivity(intent);


            }
            return true;
        }
        else if (item.getItemId() == R.id.menuA) {
            if(hasNetworkConnection()) {
                Intent data = new Intent(this, DailyForecastActivity.class);
                if (degree) { // its True here

                    degree = false;

                    data.putExtra("degreeFarenheit", degree);
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.units_c));

                        DataLoaderVolley.HomedownloadWeather(this , city , degree);


            

                } else {
                    degree = true;
                    data.putExtra("degreeFarenheit", degree);
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.units_f));

                        DataLoaderVolley.HomedownloadWeather(this , city , degree);




                }
                saveData();
            }
            else{
                Toast.makeText(this," This function cannot be used when there is no network." ,Toast.LENGTH_SHORT).show();
            }

            return true;

        }

        else if (item.getItemId() == R.id.menuC) {

            if(hasNetworkConnection()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Create an edittext and set it to be the builder's view
                final EditText et = new EditText(this);
                et.setInputType(InputType.TYPE_CLASS_TEXT);
                et.setGravity(Gravity.CENTER_HORIZONTAL);
                builder.setView(et);

                builder.setTitle("Enter Location");
                builder.setMessage("For US Location, enter as 'City', or 'City, State'\n For international  locations enter as 'City,Country'");


                // lambda can be used here (as is below)
                builder.setPositiveButton("OK", (dialog, id) -> {
                    city = et.getText().toString().trim();

                        DataLoaderVolley.HomedownloadWeather(this , city , degree);


setTitle(city);
                });

                // lambda can be used here (as is below)
                builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                saveData();
                dialog.show();
            }
            else{
                Toast.makeText(getApplicationContext(),"This function requires devices to be connected to the internet.",Toast.LENGTH_LONG).show();
            }

            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }




}



























