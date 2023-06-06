package com.anwesha.chicagoillinois;

import java.io.Serializable;
import java.util.List;

public class Daily implements Serializable {
    public String datetimeEpoch;
    public String temp;
    public String precipprob;
    public String uvindex;
    public String description;
    public String MorningTemp;
    public String AfternoonTemp;
    public String EveningTemp;
    public String NightTemp;
    public String icon;

    public Daily(
                 String temp, String description,String datetimeEpoch,String precipprob,String uvindex,
                 String icon,String morningTemp, String afternoonTemp, String eveningTemp, String nightTemp) {
        this.datetimeEpoch = datetimeEpoch;
        this.temp = temp;

        this.precipprob = precipprob;
        this.uvindex = uvindex;
        this.description = description;
        MorningTemp = morningTemp;
        AfternoonTemp = afternoonTemp;
        EveningTemp = eveningTemp;
        NightTemp = nightTemp;
        this.icon = icon;
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

    public String getPrecipprob() {
        return precipprob;
    }

    public void setPrecipprob(String precipprob) {
        this.precipprob = precipprob;
    }

    public String getUvindex() {
        return uvindex;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMorningTemp() {
        return MorningTemp;
    }

    public void setMorningTemp(String morningTemp) {
        MorningTemp = morningTemp;
    }

    public String getAfternoonTemp() {
        return AfternoonTemp;
    }

    public void setAfternoonTemp(String afternoonTemp) {
        AfternoonTemp = afternoonTemp;
    }

    public String getEveningTemp() {
        return EveningTemp;
    }

    public void setEveningTemp(String eveningTemp) {
        EveningTemp = eveningTemp;
    }

    public String getNightTemp() {
        return NightTemp;
    }

    public void setNightTemp(String nightTemp) {
        NightTemp = nightTemp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}