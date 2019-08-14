package com.team11.dataanalytics.utils;

import com.csvreader.CsvReader;
import com.team11.dataanalytics.domain.MinuteData;
import com.team11.dataanalytics.domain.StockData;
import com.team11.dataanalytics.domain.TestData;
import com.team11.dataanalytics.openfeign.client.PythonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

public class GetDataUtil {


    @Autowired
    private PythonClient pythonClient;


    public static ArrayList<MinuteData> getDataWith1Min(String symbol,String date)
    {
        ArrayList<MinuteData> data=new ArrayList<>();
        //读取文件data
        String filepath="target/classes/ProcessedData/"+symbol+"/1.csv";
        data = GetDataUtil.readMinuteData(filepath,date);

        return  data;
    }

    public static ArrayList<TestData> getDataWith3Min(String symbol)
    {
        ArrayList<TestData> data=new ArrayList<>();
        //读取文件data
        String filepath="target/classes/ProcessedData/"+symbol+"/3.csv";
        data = GetDataUtil.read(filepath);

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
    public static ArrayList<TestData> read(String filePath){

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
                        csvReader.get("Time"),
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

    public static ArrayList<MinuteData> readMinuteData(String filePath,String date){

        ArrayList<MinuteData> data=new ArrayList<>();
        System.out.println("start reading"+filePath+"|"+date);
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());

                System.out.println(csvReader.get("Date").replace('/','-').replace("-0","-")+"|"+date);
                if(csvReader.get("Date").replace('/','-').replace("-0","-").equals(date)) {
                    System.out.println(csvReader.get("Date")+date);
                    MinuteData minuteData = new MinuteData(csvReader.get("Date"),
                            csvReader.get("Time"),
                            csvReader.get("Close"),
                            String.valueOf((Float.valueOf(csvReader.get("High")) +
                                    Float.valueOf(csvReader.get("Low"))) / 2),
                            csvReader.get("Volume"));
                    data.add(minuteData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
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
