package com.team11.dataanalytics.data;

import com.alibaba.fastjson.JSONObject;
import com.team11.dataanalytics.openfeign.client.PythonClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class PythonDataReader {
    @Autowired
    private PythonClient pythonClient;

    private List<Data> ParseData(String result, String symbol, String flag){
        List<Data> data = new ArrayList<Data>();

        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        Set<String> sortSet = new TreeSet<String>(Comparator.naturalOrder());
        sortSet.addAll(jsonObject.keySet());

        System.out.println(sortSet.toString());
        for (String key : sortSet) {
            JSONObject subObject = jsonObject.getJSONObject(key);
            String open = subObject.getString("Open");
            String high = subObject.getString("High");
            String low = subObject.getString("Low");
            String close = subObject.getString("Close");
            String volume = subObject.getString("Volume");
            String split = subObject.getString("Split Factor");
            String earnings = subObject.getString("Earnings");
            String dividends = subObject.getString("Dividends");

            Data each;

            switch (flag) {
                case "min":
                    String date = key.split(" ")[0];
                    String time = key.split(" ")[1];
                    each = new MinutesData(symbol, date, time, open, high, low, close,
                            volume, split, earnings, dividends);
                    break;
                case "day":
                    date = key;
                    each = new DailyData(symbol, date, open, high, low, close,
                            volume, split, earnings, dividends);
                    break;
                default:
                    return new ArrayList<>();

            }

            data.add(each);
        }
        return data;
    }

    private List<Data> ParseData(String result, String symbol){
        return this.ParseData(result, symbol, "min");
    }

    /*
    * GetDataBySymble
    * Param:
    *       symbol: symbol of stock
    * Return:
    *       List of Data Object
    * */
    public List<Data> GetDataBySymbol(String symbol){
        // Default get all data of 1 minutes

        String result = pythonClient.pythonGetDataBySymbol(symbol);

        return new ArrayList<Data>(this.ParseData(result, symbol));
    }

    /*
    * GetDataBySymbolSlice
    * Param:
    *       symbol: stock symbol
    *       flag:   "day" or "min"
    *       slice:  for "day", 1/3/5/10/21
    *               for "min", 1/3/5/7/15/30/60/120/300
    * return:
    *       list of Data Object
    * */
    public List<Data> GetDataBySymbolSlice(String symbol, String flag, String slice){
        String result = this.pythonClient.pythonGetDataBySymbolSlice(symbol, flag, slice);
        return this.ParseData(result, symbol, flag);
    }



}
