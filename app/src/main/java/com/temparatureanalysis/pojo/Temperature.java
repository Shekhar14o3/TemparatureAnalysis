package com.temparatureanalysis.pojo;

/**
 * Created by Techno Blogger on 25/1/17.
 */
public class Temperature {

    private String cityName, temparature;

    public Temperature(String cityName, String temparature) {
        this.cityName = cityName;
        this.temparature = temparature;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemparature() {
        return temparature;
    }

    public void setTemparature(String temparature) {
        this.temparature = temparature;
    }
}
