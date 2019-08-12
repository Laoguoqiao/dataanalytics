package com.team11.dataanalytics.data;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DataReaderTest {
    @Test
    public void read() {
        String root = "C:\\Users\\宝是宝藏的宝\\Desktop\\learning\\Trading_Strategy\\DataAnalytics\\Data Analytics\\ProcessedData\\";
        DataReader dataReader = new DataReader(root);
        Map<String, List<Data>> datas = dataReader.read("a", "2016\\01\\04", "09:30");
        for(String data: datas.keySet()){
            if(data.matches("\\d+_day"))
                System.out.println(datas.get(data));
        }
    }
}
