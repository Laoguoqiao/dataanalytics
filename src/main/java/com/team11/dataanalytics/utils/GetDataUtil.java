package com.team11.dataanalytics.utils;

import com.csvreader.CsvReader;
import com.team11.dataanalytics.data.DailyData;
import com.team11.dataanalytics.data.Data;
import com.team11.dataanalytics.data.MinutesData;
import com.team11.dataanalytics.data.PythonDataReader;
import com.team11.dataanalytics.domain.MinuteData;
import com.team11.dataanalytics.domain.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetDataUtil {


    @Autowired
    public PythonDataReader pythonDataReader;


    public ArrayList<MinuteData> getDataWith1Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, null, null);

        return data;
    }

    public ArrayList<MinuteData> getDataWith3Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, "min", "3");

        return data;
    }

    public ArrayList<MinuteData> getDataWith5Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, "min", "5");
        ;
        return data;
    }

<<<<<<< HEAD
    public List<Data> getDataWith1Day(String symbol, String start, String end) {
        List<Data> data = this.pythonDataReader.GetDataBySymbolSlice(symbol,"day","1",start, end);
        // data = readDailyData(symbol,null);
=======
    public ArrayList<MinuteData> getDataWith15Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, "min", "15");
        ;
        return data;
    }

    public ArrayList<MinuteData> getDataWith30Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, "min", "30");
        ;
        return data;
    }

    public ArrayList<MinuteData> getDataWith60Min(String symbol, String date) {
        ArrayList<MinuteData> data = new ArrayList<>();
        data = readMinuteData(symbol, date, date, "min", "60");
        ;
        return data;
    }

    public ArrayList<DailyData> getDataWith1Day(String symbol) {
        ArrayList<DailyData> data = new ArrayList<>();
        data = readDailyData(symbol,null);
>>>>>>> 6a15221ce71943d12ecabda3e5c506580ab82277
        return data;
    }

    public  List<Data> getLastDay() {
        List<Data> data = new ArrayList<>();
        data = this.pythonDataReader.GetOtherData("lastday");
        //data = readDailyData(null,"lastday");
        return data;
    }

    public List<Data> getYahooData(String symbol, String start, String end){
        List<Data> data = this.pythonDataReader.GetYahooData(symbol, start, end);
        return data;
    }

    public ArrayList<MinuteData> readMinuteData(String symbol, String start, String end, String flag, String slice) {

        ArrayList<MinuteData> data = new ArrayList<>();

        List<Data> originDatas = new ArrayList<Data>();
        if (flag == null & slice == null) {
            //远程获取一天分钟时间数据(原始数据）
            originDatas = pythonDataReader.GetOriginData(symbol, start, end);
        } else if (flag != null & slice != null) {
            originDatas = pythonDataReader.GetDataBySymbolSlice(symbol, flag, slice, start, end);
        }
        for (Data originData : originDatas) {
            // 读一整行
            //System.out.println(csvReader.getRawRecord());
            MinutesData minutesData = (MinutesData) originData;
            if (minutesData.getDate().replace("-0", "-").equals(start) ||
                    minutesData.getDate().replace("-0", "-").equals(end)) {
                minutesData.setDate(minutesData.getDate().replace("-0", "-").toString());
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


    public ArrayList<DailyData> readDailyData(String symbol,String flag) {

        ArrayList<DailyData> data = new ArrayList<>();
        List<Data> originDatas = new ArrayList<>();
        if(flag==null) {
           originDatas= pythonDataReader.GetDataBySymbol(symbol);
        }else {
            originDatas=pythonDataReader.GetOtherData(flag);
        }


        for (Data originData : originDatas) {
            DailyData dailyData = (DailyData) originData;
            data.add(dailyData);
        }
        return data;
    }

//    public static ArrayList<StockData> readStockData(String filePath) {
//        ArrayList<StockData> data = new ArrayList<>();
//        System.out.println("start reading");
//        try {
//            // 创建CSV读对象
//            CsvReader csvReader = new CsvReader(filePath);
//
//            // 读表头
//            csvReader.readHeaders();
//            while (csvReader.readRecord()) {
//                // 读一整行
//                //System.out.println(csvReader.getRawRecord());
//                StockData stockData = new StockData(csvReader.get("Symbol"),
//                        csvReader.get("Date"),
//                        csvReader.get("Open"),
//                        csvReader.get("High"),
//                        csvReader.get("Low"),
//                        csvReader.get("Close"),
//                        csvReader.get("Volume"),
//                        csvReader.get("Split Factor"),
//                        csvReader.get("Earnings"),
//                        csvReader.get("Dividends"));
//                data.add(stockData);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }

}
