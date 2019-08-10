package com.team11.dataanalytics.data;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class DataProcessTest {

    @Test
    public void getALL() {
        DataProcess dataProcess = new DataProcess();
        String root = "C:\\Users\\宝是宝藏的宝\\Desktop\\learning\\Trading_Strategy\\DataAnalytics\\Data Analytics\\Test Data\\Quant Quote Market Data - Jan to Mar 2016";

        dataProcess.getALL(root);
        System.out.println(dataProcess.all_data.size());
    }
}
