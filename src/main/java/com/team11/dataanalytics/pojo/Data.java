package com.team11.dataanalytics.pojo;

import java.io.Serializable;

public class Data implements Serializable {

    private static final long serialVersionUID = -4803762426713942681L;

    private String name;
    private int date;
    private int time;
    private float open;
    private float high;
    private float low;
    private float close;
    private int volumn;
    private int split_factor;
    private int earning;
    private int dividends;

    public Data(String name, int date, int time, float open, float high, float low, float close, int volumn, int split_factor, int earning, int dividends) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volumn = volumn;
        this.split_factor = split_factor;
        this.earning = earning;
        this.dividends = dividends;
    }

    public Data(String date, String time, String open, String high, String low, String close, String volumn, String split_factor, String earning, String dividends) {
        this.date = Integer.parseInt(date);
        this.time = Integer.parseInt(time);
        this.open = Float.parseFloat(open);
        this.high = Float.parseFloat(high);
        this.low = Float.parseFloat(low);
        this.close = Float.parseFloat(close);
        this.volumn = Integer.parseInt(volumn);
        this.split_factor = Integer.parseInt(split_factor);
        this.earning = Integer.parseInt(earning);
        this.dividends = Integer.parseInt(dividends);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public int getVolumn() {
        return volumn;
    }

    public void setVolumn(int volumn) {
        this.volumn = volumn;
    }

    public int getSplit_factor() {
        return split_factor;
    }

    public void setSplit_factor(int split_factor) {
        this.split_factor = split_factor;
    }

    public int getEarning() {
        return earning;
    }

    public void setEarning(int earning) {
        this.earning = earning;
    }

    public int getDividends() {
        return dividends;
    }

    public void setDividends(int dividends) {
        this.dividends = dividends;
    }
}
