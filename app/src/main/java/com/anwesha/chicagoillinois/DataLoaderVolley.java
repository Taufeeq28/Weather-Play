package com.anwesha.chicagoillinois;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataLoaderVolley {


    private static MainActivity mainActivity;
    private static DailyForecastActivity dailyForecastActivity;
    private static RequestQueue queue;
    private static RequestQueue newqueue;
    private static Daily dayWeatherObj;
    private static Weather weatherObj;


    public static String city = "Chicago, IL";
    public static boolean fahrenheit = true;


    private static final String weatherURL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
    private static final String yourAPIKey = "C3NSQFXDBCXQSFDH433ABHCZ5";



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void HomedownloadWeather(MainActivity mainActivity , String city , boolean fahrenheit){

        queue = Volley.newRequestQueue(mainActivity);
        // Getting the string
        String urlBuild = weatherURL + "/" + city + "?" + "unitGroup=" + (fahrenheit ? "us" : "metric")  + "&lang=en" + "&key=" + yourAPIKey;


        Response.Listener<JSONObject> listener =
                response -> {
                    parseJSON(response.toString());
                    mainActivity.updateData(weatherObj);


                };

        Response.ErrorListener error =
                error1 -> {Toast.makeText(mainActivity, "No network connection", Toast.LENGTH_SHORT).show();

                };

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlBuild,
                        null, listener, error);

        queue.add(jsonObjectRequest);
    }

    public static void DaydownloadWeather(DailyForecastActivity dailyForecastActivity , String city , boolean fahrenheit ){

        queue = Volley.newRequestQueue(dailyForecastActivity);

        // Getting the string
        String urlBuild = weatherURL + "/" + city + "?" + "unitGroup=" + (fahrenheit ? "us" : "metric")  + "&lang=en" + "&key=" + yourAPIKey;

        Response.Listener<JSONObject> listener =
                response -> {
                    parseJSON(response.toString());
                    dailyForecastActivity.updateData(weatherObj);

                };

        Response.ErrorListener error =
                error1 -> Toast.makeText(dailyForecastActivity, "No network connection", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlBuild,
                        null, listener, error);

        queue.add(jsonObjectRequest);
    }




    private static void parseJSON(String s) {

        try {

            ArrayList<JSONObject>  dayArrayList= new ArrayList<>();


            JSONObject jsonObject = new JSONObject(s);

            String address = jsonObject.getString("address");
            JSONArray jsonDays = jsonObject.getJSONArray("days");

            for (int i = 0; i < jsonDays.length(); i++) {

                JSONObject Object1 = jsonDays.getJSONObject(i);


                dayArrayList.add(Object1);

                JSONArray jsonHours = Object1.getJSONArray("hours");

                for (int j = 0; j < jsonHours.length(); j++) {


                }
                // Log.d(TAG , "The number of hours " + accumHours);



            }




            // CurrentConditions

            JSONObject jsonObjectcurrentCond = jsonObject.getJSONObject("currentConditions");
            String currentTzOffSet = jsonObject.getString("tzoffset");
            String currentDateTimeEpoch = jsonObjectcurrentCond.getString("datetimeEpoch");
            String currentTemp = jsonObjectcurrentCond.getString("temp");
            String currentVisibility = jsonObjectcurrentCond.getString("visibility");
            String currentFeelsLike = jsonObjectcurrentCond.getString("feelslike");
            String currentHumidity = jsonObjectcurrentCond.getString("humidity");
            String currentUvIndex = jsonObjectcurrentCond.getString("uvindex");
            String currentIcon = jsonObjectcurrentCond.getString("icon");
            String currentCloudCover = jsonObjectcurrentCond.getString("cloudcover");
            String currentCondition = jsonObjectcurrentCond.getString("conditions");
            String currentWindSpeed = jsonObjectcurrentCond.getString("windspeed");
            String currentWindDir = jsonObjectcurrentCond.getString("winddir");
            String currentWindGust = jsonObjectcurrentCond.getString("windgust");


            String currentSunriseEpoch = jsonObjectcurrentCond.getString("sunriseEpoch");
            String currentSunsetEpoch = jsonObjectcurrentCond.getString("sunsetEpoch");


            weatherObj = new Weather(currentDateTimeEpoch , currentFeelsLike , currentUvIndex , currentHumidity,
                    currentIcon, currentVisibility, currentSunsetEpoch , currentSunriseEpoch , currentCloudCover, currentCondition,
                    currentWindDir , currentWindSpeed, currentWindGust , currentTemp,dayArrayList , currentTzOffSet);

           // mainActivity.swiper.setRefreshing(false);
            //mainActivity.saveData();



        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }




}
/*import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.DialogTitle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataLoaderVolley {
    private static final String weatherURL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private static final String yourAPIKey = "C3NSQFXDBCXQSFDH433ABHCZ5";
    private static final String TAG = "DataLoaderVolley";;
    private static RequestQueue queue;
    private static RequestQueue mainqueue;
    private static Daily daily;
    private static Weather weather;
    private static Hourly hourly;
    private static Weather weatherObj;
    private static String city= "Chicago, IL";
    private static long start;
    private static MainActivity mainActivity;
    private static DailyForecastActivity dailyForecastActivity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getMainData(MainActivity mainActivity, String city, boolean f){

        queue = Volley.newRequestQueue(mainActivity);
      String unit = "us";
        if(f==true)
            unit="us";
        else
            unit="metric";

        Uri.Builder buildURL = Uri.parse(weatherURL).buildUpon();
        buildURL.appendPath(city);
        buildURL.appendQueryParameter("unitGroup",unit);
        buildURL.appendQueryParameter("lang","en");
        buildURL.appendQueryParameter("key",yourAPIKey);
        String urlToUse = buildURL.build().toString();
        Response.Listener<JSONObject> listener =
                response -> {
                    parseJSON(response.toString());
                    mainActivity.updateData(weatherObj);
                    Log.d(TAG , "Response is called");

                };

        Response.ErrorListener error =
                error1 -> {
                    Toast.makeText(mainActivity, "Error is provoked", Toast.LENGTH_SHORT).show();
                    Log.d(TAG , "Response is not getting called");
                };

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlToUse,
                        null, listener, error);

        queue.add(jsonObjectRequest);



    }





//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private static void handleMainResults(MainActivity mainActivity, String s, boolean f) {
//        String unit="";
//        if(f==true)
//            unit="us";
//        else
//            unit="metric";
//
//        Uri.Builder buildURL = Uri.parse(weatherURL).buildUpon();
//        buildURL.appendPath(city);
//        buildURL.appendQueryParameter("unitGroup",unit);
//        buildURL.appendQueryParameter("lang","en");
//        buildURL.appendQueryParameter("key",yourAPIKey);
//        String urlToUse = buildURL.build().toString();
//
//        if (s == null) {
//            mainActivity.downloadFailed();
//            return;
//        }
//
//        parseJSON(s);
//        mainActivity.updateData(weather);
//    }













    public static void getSourceData(DailyForecastActivity dailyForecastActivity, String city,boolean f) {
       queue = Volley.newRequestQueue(dailyForecastActivity);
String unit="";
if(f==true)
    unit="us";
else
    unit="metric";

      Uri.Builder buildURL = Uri.parse(weatherURL).buildUpon();
        buildURL.appendPath(city);
        buildURL.appendQueryParameter("unitGroup",unit);
        buildURL.appendQueryParameter("lang","en");
        buildURL.appendQueryParameter("key",yourAPIKey);
        String urlToUse = buildURL.build().toString();
        Response.Listener<JSONObject> listener =
                response -> {
                    parseJSON(response.toString());
                    dailyForecastActivity.updateData(weatherObj);
                    Log.d(TAG , "Response is called");

                };

        Response.ErrorListener error =
                error1 -> {Toast.makeText(mainActivity, "Error is provoked", Toast.LENGTH_SHORT).show();
                    Log.d(TAG , "Response is not getting called");
                };

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, urlToUse,
                        null, listener, error);

        queue.add(jsonObjectRequest);
    }
//    private static void handleResults(DailyForecastActivity mainActivity, String s,boolean f) {
//        String unit="";
//        if(f==true)
//            unit="us";
//        else
//            unit="metric";
//
//        Uri.Builder buildURL = Uri.parse(weatherURL).buildUpon();
//        buildURL.appendPath(city);
//        buildURL.appendQueryParameter("unitGroup",unit);
//        buildURL.appendQueryParameter("lang","en");
//        buildURL.appendQueryParameter("key",yourAPIKey);
//        String urlToUse = buildURL.build().toString();
//        if (s == null) {
//            mainActivity.downloadFailed();
//            return;
//        }
//        parseJSON(s);
//        mainActivity.updateData(weather);
//    }


    private static void parseJSON(String s) {

        try {

            ArrayList<JSONObject>  dayArrayList= new ArrayList<>();


            JSONObject jsonObject = new JSONObject(s);

            // The outer
            String address = jsonObject.getString("address");
            // String description = jsonObject.getString("description");



            /// Accessing number of days Array
            JSONArray jsonArrayDays = jsonObject.getJSONArray("days");
            int accum = 0;
            for (int i = 0; i < jsonArrayDays.length(); i++) {
                accum+= 1;
                JSONObject jsonObject1 = jsonArrayDays.getJSONObject(i);


                dayArrayList.add(jsonObject1);

                JSONArray jsonArrayHours = jsonObject1.getJSONArray("hours");

                int accumHours = 0;
                for (int j = 0; j < jsonArrayHours.length(); j++) {
                    accumHours+= 1;

                }
                // Log.d(TAG , "The number of hours " + accumHours);



            }




            // CurrentConditions

            JSONObject jsonObjectcurrentCond = jsonObject.getJSONObject("currentConditions");
            String currentTzOffSet = jsonObject.getString("tzoffset");
            String currentDateTimeEpoch = jsonObjectcurrentCond.getString("datetimeEpoch");
            String currentTemp = jsonObjectcurrentCond.getString("temp");
            String currentVisibility = jsonObjectcurrentCond.getString("visibility");
            String currentFeelsLike = jsonObjectcurrentCond.getString("feelslike");
            String currentHumidity = jsonObjectcurrentCond.getString("humidity");
            String currentUvIndex = jsonObjectcurrentCond.getString("uvindex");
            String currentIcon = jsonObjectcurrentCond.getString("icon");
            String currentCloudCover = jsonObjectcurrentCond.getString("cloudcover");
            String currentCondition = jsonObjectcurrentCond.getString("conditions");
            String currentWindSpeed = jsonObjectcurrentCond.getString("windspeed");
            String currentWindDir = jsonObjectcurrentCond.getString("winddir");
            String currentWindGust = jsonObjectcurrentCond.getString("windgust");


            String currentSunriseEpoch = jsonObjectcurrentCond.getString("sunriseEpoch");
            String currentSunsetEpoch = jsonObjectcurrentCond.getString("sunsetEpoch");


            weatherObj = new Weather(address, dayArrayList,currentDateTimeEpoch, currentTemp, currentFeelsLike,
                    currentHumidity, currentWindGust, currentWindSpeed, currentWindDir, currentVisibility,
                    currentCloudCover, currentUvIndex, currentCondition, currentIcon, currentSunriseEpoch, currentSunsetEpoch);


            for (int i = 0; i < dayArrayList.size() ; i++) {
                JSONArray jsonArrayHours = dayArrayList.get(i).getJSONArray("hours");

            }




            // Log.d(TAG, "The number of hours: " + jsonArrayHours.length());


            JSONObject jsonObjectDay1 = (JSONObject) jsonArrayDays.get(1);
            String temp = jsonObjectDay1.getString("temp");
            String datetime = jsonObjectDay1.getString("datetime");

            //Log.d(TAG, temp);
            //Log.d(TAG, datetime);

//            weatherObj = new Weather(address, description, temp, datetime);



            //Log.d(TAG , address);
            // Log.d(TAG , description);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
/*
    private static void parseJSON(String s) {


        ArrayList<JSONObject> countryList = new ArrayList<>();
        ArrayList<Hourly> hourList = new ArrayList<>();
        try {
            JSONObject job = new JSONObject(s);
            String address = job.getString("address");
            String timezone = job.getString("timezone");
            String tzoffset = job.getString("tzoffset");
            JSONArray days = job.getJSONArray("days");
            Log.d(TAG,address);
            int daycount = 0,hourcount=0;

            for (int i=0; i<days.length(); i++) {
                JSONObject jsonObjectDay = days.getJSONObject(i);
                countryList.add(jsonObjectDay);
                JSONArray jsonArrayHours = jsonObjectDay.getJSONArray("hours");

                for (int j = 0; j < jsonArrayHours.length(); j++) {
                  hourcount++;
                }
            }



             Log.d(TAG , "The number of hours " + hourcount);
            JSONObject jsonObjectcurrentCond = job.getJSONObject("currentConditions");
            String currentAddress = job.getString("address");
            long currentDateTimeEpoch = jsonObjectcurrentCond.getLong("datetimeEpoch");
            String datetimeEpoch = currentDateTimeEpoch+"";
            String currentTemp = jsonObjectcurrentCond.getString("temp");
            String currentVisibility = jsonObjectcurrentCond.getString("visibility");
            String currentFeelsLike = jsonObjectcurrentCond.getString("feelslike");
            String currentHumidity = jsonObjectcurrentCond.getString("humidity");
            String currentUvIndex = jsonObjectcurrentCond.getString("uvindex");
            String currentIcon = jsonObjectcurrentCond.getString("icon");
            String currentCloudCover = jsonObjectcurrentCond.getString("cloudcover");
            String currentCondition = jsonObjectcurrentCond.getString("conditions");
            String currentWindSpeed = jsonObjectcurrentCond.getString("windspeed");
            String currentWindDir = jsonObjectcurrentCond.getString("winddir");
            String currentWindGust = jsonObjectcurrentCond.getString("windgust");
            String currentSunriseEpoch = jsonObjectcurrentCond.getString("sunriseEpoch");
            String currentSunsetEpoch = jsonObjectcurrentCond.getString("sunsetEpoch");

            weather = new Weather(address, countryList,datetimeEpoch, currentTemp, currentFeelsLike,
                    currentHumidity, currentWindGust, currentWindSpeed, currentWindDir, currentVisibility,
                    currentCloudCover, currentUvIndex, currentCondition, currentIcon, currentSunriseEpoch, currentSunsetEpoch);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/
