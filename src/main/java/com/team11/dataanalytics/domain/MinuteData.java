package com.team11.dataanalytics.domain;



public class MinuteData  {
    private String date;
    private String time;
    private String current;
    private String average;
    private String volume;

    public MinuteData(String date,String time,String current,String average,String volume)
    {
        this.date=date;
        this.time=time;
        this.current=current;
        this.average=average;
        this.volume=volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
