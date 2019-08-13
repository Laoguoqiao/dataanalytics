package com.team11.dataanalytics.devkit;

import com.team11.dataanalytics.pojo.StockData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileProcessor {
    public static ArrayList<StockData> readCSV(String path){
        ArrayList<StockData> stocks = new ArrayList<StockData>();
        File csvFile = new File(path);

        try {
            FileReader fileReader = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fileReader);
            boolean sign=false; //skip the firstline
            while(br.ready()){
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line,",");
                String symbol, date, open, high, low, close, volume, split_factor, earnings, dividends;
                if(st.hasMoreTokens() && sign){
                    date = st.nextToken().trim();
                    open = st.nextToken().trim();
                    high = st.nextToken().trim();
                    low = st.nextToken().trim();
                    close = st.nextToken().trim();
                    volume = st.nextToken().trim();
                    split_factor = st.nextToken().trim();
                    earnings = st.nextToken().trim();
                    dividends = st.nextToken().trim();
                    StockData stockData = new StockData(date, open, high, low, close, volume, split_factor, earnings, dividends);
                    stocks.add(stockData);
                }
                else
                    sign = true;
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return stocks;
    }
}
