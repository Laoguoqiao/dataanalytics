package com.team11.dataanalytics.domain;

public class StockData {
    private String symbol;
    private String date;
    private String open;
    private String close;
    private String high;
    private String low;
    private String volume;
    private String split_factor;
    private String earnings;
    private String dividends;

     public StockData(String symbol,String date,String open,String high,String low,String close,String volume,String split_factor,String earnings,String dividends){
         this.symbol=symbol;
         this.date=date;
         this.open=open;
         this.high=high;
         this.low=low;
         this.close=close;
         this.volume=volume;
         this.split_factor=split_factor;
         this.earnings=earnings;
         this.dividends=dividends;
     }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
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

    public String getSplit_factor() {
        return split_factor;
    }

    public void setSplit_factor(String split_factor) {
        this.split_factor = split_factor;
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
