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
    private List<String> parameters = new ArrayList<>();

    public PythonDataReader() {
        this.parameters.add("symbol");
        this.parameters.add("flag");
        this.parameters.add("slices");
        this.parameters.add("start");
        this.parameters.add("end");
        this.parameters.add("MACD");
        this.parameters.add("RSI");
        this.parameters.add("KDJ");
    }

    private Map<String, String> SetParam(String... params){
        Map<String, String> map = new HashMap<>();
        int i = 0;
        for(String param:params){
            map.put(this.parameters.get(i), param);
            i ++;
        }
        if(i==0){
            map.put(this.parameters.get(i), "a");
            i ++;
        }
        if(i==1){
            map.put(this.parameters.get(i), "day");
            i ++;
        }
        if(i==2){
            map.put(this.parameters.get(i), "1");
            i ++;
        }
        if(i==3){
            map.put(this.parameters.get(i), "2016-01-04");
            i ++;
        }
        if(i==4){
            map.put(this.parameters.get(i), "2016-03-24");
            i ++;
        }
        if(i==5){
            map.put(this.parameters.get(i++), "False");
            map.put(this.parameters.get(i++), "False");
            map.put(this.parameters.get(i++), "False");
        }
        return map;
    }

    private List<Data> ParseOtherData(String result, String flag){
        if(flag.equals("lastday"))
            return this.ParseData(result, flag);
        return new ArrayList<Data>();
    }

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
                case "yahoo":
                    date = key;
                    each = new DailyData(symbol, date, open, high, low, close,
                            volume, "0", "0", "0");
                    break;
                default:
                    return new ArrayList<>();

            }

            data.add(each);
        }
        return data;
    }

    private List<Data> ParseData(String result, String symbol){
        return this.ParseData(result, symbol, "day");
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

        String result = pythonClient.pythonGetDataByDict(this.SetParam(symbol));

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
    public List<Data> GetDataBySymbolSlice(String symbol, String flag, String slice, String start, String end){
        String result = this.pythonClient.pythonGetDataByDict(SetParam(symbol, flag, slice, start, end));
        return this.ParseData(result, symbol, flag);
    }

    public List<Data> GetOriginData(String symbol, String start, String end){
        Map<String, String> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("start", start);
        map.put("end", end);
        String result = this.pythonClient.pythonGetOriginData(map);
        return this.ParseData(result, symbol, "min");
    }

    public List<Data> GetOtherData(String flag){
        Map<String, String> map = new HashMap<>();
        map.put("flag", flag);
        String result = this.pythonClient.pythonGetOtherData(map);
        return this.ParseOtherData(result,flag);
    }

    public List<Data> GetYahooData(String symbol, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("start", start);
        map.put("end", end);
        String result = this.pythonClient.pythonGetYahooData(map);
        return this.ParseData(result, symbol, "yahoo");
    }
}
