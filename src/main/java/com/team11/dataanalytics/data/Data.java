package com.team11.dataanalytics.data;

public class Data {

    public float open;
    public float high;
    public float low;
    public float close;
    public float volume;
    public float split_factor;
    public float earning;
    public float dividends;
    public String symbol;

    public Data(String symbol, String open, String high, String low, String close, String volume, String split_factor, String earning, String dividends) {
        this.symbol = symbol;
        this.open = Float.parseFloat(open);
        this.high = Float.parseFloat(high);
        this.low = Float.parseFloat(low);
        this.close = Float.parseFloat(close);
        this.volume = Float.parseFloat(volume);
        this.split_factor = Float.parseFloat(split_factor);
        this.earning = Float.parseFloat(earning);
        this.dividends = Float.parseFloat(dividends);
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getSplit_factor() {
        return split_factor;
    }

    public void setSplit_factor(float split_factor) {
        this.split_factor = split_factor;
    }

    public float getEarning() {
        return earning;
    }

    public void setEarning(float earning) {
        this.earning = earning;
    }

    public float getDividends() {
        return dividends;
    }

    public void setDividends(float dividends) {
        this.dividends = dividends;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Data{" +
                "open=" + open +
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
