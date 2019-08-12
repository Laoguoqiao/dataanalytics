package com.team11.dataanalytics.data;

public class MinutesData extends DailyData{
    private String time;

    public MinutesData(String symbol, String date, String time, String open, String high, String low, String close, String volume, String split_factor, String earning, String dividends) {
        super(symbol, date, open, high, low, close, volume, split_factor, earning, dividends);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MinutesData{" +
                "time='" + time + '\'' +
                ", Date='" + Date + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", split_factor=" + split_factor +
                ", earning=" + earning +
                ", dividends=" + dividends +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
