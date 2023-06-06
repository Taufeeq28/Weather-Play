package com.anwesha.chicagoillinois;

import androidx.arch.core.internal.SafeIterableMap;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Weather implements Serializable {
    public String address;
    public String timezone;
    public String offset;
    //public List<Hourly> hours;
    public ArrayList<JSONObject> days;
    public String datetimeEpoch;
    public String temp;
    public String feelslike;
    public String humidity;
    public String windgust;
    public String windspeed;
    public String winddir;
    public String visiblility;
    public String cloudcover;
    public String uvindex;
    public String conditions;
    public String icon;
    public String sunriseEpoch;
    public String sunsetEpoch;


    public Weather(String datetimeEpoch,String feelslike,String uvindex,
                   String humidity,String icon, String visiblility,  String sunsetEpoch,  String sunriseEpoch,
                   String cloudcover,String conditions,
                   String winddir, String windspeed, String windgust,String temp,ArrayList<JSONObject>days,String offset)
                   {
        this.address = address;
        this.days = days;
        this.datetimeEpoch = datetimeEpoch;
        this.temp = temp;
        this.feelslike = feelslike;
        this.humidity = humidity;
        this.windgust = windgust;
        this.windspeed = windspeed;
        this.winddir = winddir;
        this.visiblility = visiblility;
        this.cloudcover = cloudcover;
        this.uvindex = uvindex;
        this.conditions = conditions;
        this.icon = icon;
        this.sunriseEpoch = sunriseEpoch;
        this.sunsetEpoch = sunsetEpoch;
        //this.hours=hours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<JSONObject> getDays() {
        return days;
    }

    public void setDays(ArrayList<JSONObject> days) {
        this.days = days;
    }

    public String getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public void setDatetimeEpoch(String datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(String feelslike) {
        this.feelslike = feelslike;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindgust() {
        return windgust;
    }

    public void setWindgust(String windgust) {
        this.windgust = windgust;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddir() {
        return winddir;
    }

    public void setWinddir(String winddir) {
        this.winddir = winddir;
    }

    public String getVisiblility() {
        return visiblility;
    }

    public void setVisiblility(String visiblility) {
        this.visiblility = visiblility;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(String cloudcover) {
        this.cloudcover = cloudcover;
    }

    public String getUvindex() {
        return uvindex;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSunriseEpoch() {
        return sunriseEpoch;
    }

    public void setSunriseEpoch(String sunriseEpoch) {
        this.sunriseEpoch = sunriseEpoch;
    }

    public String getSunsetEpoch() {
        return sunsetEpoch;
    }

    public void setSunsetEpoch(String sunsetEpoch) {
        this.sunsetEpoch = sunsetEpoch;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

//    public List<Hourly> getHours() {
//        return hours;
//    }
//
//    public void setHours(List<Hourly> hours) {
//        this.hours = hours;
//    }
}

