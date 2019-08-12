package com.team11.dataanalytics.data;

public class DailyData extends Data {

    public String Date;

    public DailyData(String symbol, String date, String open, String high, String low, String close, String volume, String split_factor, String earning, String dividends) {
        super(symbol, open, high, low, close, volume, split_factor, earning, dividends);
        this.Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "DailyData{" +
                "Date='" + Date + '\'' +
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
