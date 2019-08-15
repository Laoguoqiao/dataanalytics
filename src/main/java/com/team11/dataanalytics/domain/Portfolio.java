package com.team11.dataanalytics.domain;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Portfolio {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")

    private String pid;

    private String uid;

    private String stockList;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStockList() {
        return stockList;
    }

    public void setStockList(String stockList) {
        this.stockList = stockList;
    }

    public void updateStockList(List<String> stockList){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<stockList.size()-1;i++){
            sb.append(stockList.get(i));
            sb.append(",");
        }
        sb.append(stockList.get(stockList.size()-1));
        this.setStockList(sb.toString());
    }

}
