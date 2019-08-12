package com.team11.dataanalytics.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    private int sid;

    private String symbol;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
