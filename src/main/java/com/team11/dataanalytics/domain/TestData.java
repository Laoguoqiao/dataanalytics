package com.team11.dataanalytics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


public class TestData {

    private String date;
    private String time;
    private String open;
    private String close;
    private String high;
    private String low;
    private String volume;
    private String splitFactor;
    private String earnings;
    private String divilends;

    public TestData(String date, String time, String open, String close, String high, String low, String volume, String splitFactor, String earnings, String divilends) {
        this.date = date;
        this.time = time;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.splitFactor = splitFactor;
        this.earnings = earnings;
        this.divilends = divilends;
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

    public String getDivilends() {
        return divilends;
    }

    public void setDivilends(String divilends) {
        this.divilends = divilends;
    }
}
