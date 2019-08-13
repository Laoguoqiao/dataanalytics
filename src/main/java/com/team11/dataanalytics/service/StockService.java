package com.team11.dataanalytics.service;

import com.team11.dataanalytics.devkit.FileProcessor;
import com.team11.dataanalytics.pojo.StockData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StockService {
    private FileProcessor fileProcessor = new FileProcessor();

    public ArrayList<StockData> getStockData(String path){
        ArrayList<StockData> stocks = fileProcessor.readCSV(path);
        System.out.println(stocks.size());
        return stocks;
    }
}
