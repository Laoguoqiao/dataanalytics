package com.team11.dataanalytics.pojo;

public class StockData {
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private String splitFactor;
    private String earnings;
    private String dividends;

    public StockData(String date, String open, String high, String low, String close, String volume, String splitFactor, String earnings, String dividends) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.splitFactor = splitFactor;
        this.earnings = earnings;
        this.dividends = dividends;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSplitFactor() {
        return splitFactor;
    }

    public void setSplitFactor(String splitFactor) {
        this.splitFactor = splitFactor;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getDividends() {
        return dividends;
    }

    public void setDividends(String dividends) {
        this.dividends = dividends;
    }

}
