package com.team11.dataanalytics.utils;

import com.csvreader.CsvReader;
import com.team11.dataanalytics.data.Data;
import com.team11.dataanalytics.data.MinutesData;
import com.team11.dataanalytics.data.PythonDataReader;
import com.team11.dataanalytics.domain.MinuteData;
import com.team11.dataanalytics.domain.StockData;
import com.team11.dataanalytics.domain.TestData;
import com.team11.dataanalytics.openfeign.client.PythonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetDataUtil {


   @Autowired
    PythonDataReader pythonDataReader;


    public  ArrayList<MinuteData> getDataWith1Min(String symbol,String date)
    {
        ArrayList<MinuteData> data=new ArrayList<>();
        data = readMinuteData(symbol,date,date,null,null);

        return  data;
    }

    public static ArrayList<TestData> getDataWith3Min(String symbol)
    {
        ArrayList<TestData> data=new ArrayList<>();
        data = read3MinData(symbol,);

        return  data;
    }

    public static ArrayList<TestData> getDataWith5Min(String symbol)
    {
        ArrayList<TestData> data=new ArrayList<>();
        //读取文件data
        String filepath="target/classes/ProcessedData/"+symbol+"/5.csv";
        data = GetDataUtil.read(filepath);

        return  data;
    }

    public static ArrayList<TestData> getDataWith1Day(String symbol)
    {
        ArrayList<TestData> data=new ArrayList<>();
        //读取文件data
        String filepath="target/classes/ProcessedData/"+symbol+"/1_day.csv";
        data = GetDataUtil.readWithoutTime(filepath);

        return  data;
    }

    public static ArrayList<StockData> getStockData()
    {
        ArrayList<StockData> data=new ArrayList<>();

        String filepath="target/classes/ProcessedData/lastday.csv";
        data=GetDataUtil.readStockData(filepath);
        return data;

    }

    public  ArrayList<MinuteData> readMinuteData(String symbol,String start,String end,String flag,String slice){

        ArrayList<MinuteData> data=new ArrayList<>();

        List<Data> originDatas=new ArrayList<>();
        if(flag==null&slice==null){
            //远程获取一天分钟时间数据(原始数据）
            originDatas=pythonDataReader.GetOriginData(symbol,start,end);
        }else if(flag!=null&slice!=null){
            originDatas=pythonDataReader.GetDataBySymbolSlice(symbol,flag,slice);
        }
           for(Data originData:originDatas){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
               MinutesData minutesData= (MinutesData) originData;
                if(minutesData.getDate().replace("-0","-").equals(start)||
                        minutesData.getDate().replace("-0","-").equals(end)) {
                    minutesData.setDate(minutesData.getDate().replace("-0","-").toString());
                    MinuteData minuteData = new MinuteData(minutesData.getDate(),
                            minutesData.getTime(),
                            String.valueOf(minutesData.getClose()),
                            String.valueOf((minutesData.getHigh() +
                                    minutesData.getLow()) / 2),
                           String.valueOf(minutesData.getVolume()));
                    data.add(minuteData);
                }
            }
        return data;
    }

    public static ArrayList<TestData> readWithoutTime(String filePath){

        ArrayList<TestData> data=new ArrayList<>();
        System.out.println("start reading");
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                TestData testData = new TestData(csvReader.get("Date"),
                        "0",
                        csvReader.get("Open"),
                        csvReader.get("Close"),
                        csvReader.get("High"),
                        csvReader.get("Low"),
                        csvReader.get("Volume"),
                        csvReader.get("Split Factor"),
                        csvReader.get("Earnings"),
                        csvReader.get("Dividends"));
                data.add(testData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<StockData> readStockData(String filePath)
    {
        ArrayList<StockData> data=new ArrayList<>();
        System.out.println("start reading");
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                StockData stockData = new StockData(csvReader.get("Symbol"),
                        csvReader.get("Date"),
                        csvReader.get("Open"),
                        csvReader.get("High"),
                        csvReader.get("Low"),
                        csvReader.get("Close"),
                        csvReader.get("Volume"),
                        csvReader.get("Split Factor"),
                        csvReader.get("Earnings"),
                        csvReader.get("Dividends"));
                data.add(stockData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
