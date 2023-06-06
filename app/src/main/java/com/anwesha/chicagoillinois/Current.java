package com.anwesha.chicagoillinois;

import java.io.Serializable;

public class Current implements Serializable {
    public String datetimeEpoch;
    public String temp;
    public String feelslike;
    public String humidity;
    public String windgust;
    public String windspeed;
    public String visiblility;
    public String cloudcover;
    public String uvindex;
    public String conditions;
    public String icon;
    public String sunriseEpoch;
    public String sunsetEpoch;

    public Current(String datetimeEpoch, String temp,
                   String feelslike, String humidity, String windgust, String windspeed,
                   String visiblility, String cloudcover,
                   String uvindex, String conditions, String icon,
                   String sunriseEpoch, String sunsetEpoch) {
        this.datetimeEpoch = datetimeEpoch;
        this.temp = temp;
        this.feelslike = feelslike;
        this.humidity = humidity;
        this.windgust = windgust;
        this.windspeed = windspeed;
        this.visiblility = visiblility;
        this.cloudcover = cloudcover;
        this.uvindex = uvindex;
        this.conditions = conditions;
        this.icon = icon;
        this.sunriseEpoch = sunriseEpoch;
        this.sunsetEpoch = sunsetEpoch;
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
}
