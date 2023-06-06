package com.anwesha.chicagoillinois;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hourly implements Serializable {
    private final String day;
    private final  String time;
    private final  String temp;
    private final  String conditions;
    private final  String icon;

    public Hourly(String day,String time, String temp, String conditions, String icon) {
        this.day=day;
        this.time=time;
        this.temp = temp;
        this.conditions = conditions;
        this.icon = icon;
        //this.weather=weather;
    }

    public String getDay() {
        return day;
    }


    public String getTime() {
        return time;
    }



    public String getTemp() {
        return temp;
    }



    public String getConditions() {
        return conditions;
    }


    public String getIcon() {
        return icon;
    }


}
