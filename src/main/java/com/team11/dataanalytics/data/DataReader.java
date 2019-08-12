package com.team11.dataanalytics.data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader {
    private String root;
    private String symbol;
    private List<Data> datas = new ArrayList<Data>();
    private Map<String, List<Data>> all_datas = new HashMap<String, List<Data>>();

    public DataReader(String root) {
        this.root = root;
    }

    public boolean read_csv(String filepath){
        // Get data of one csv
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
            int length = filepath.split("\\\\").length;
            String filename = filepath.split("\\\\")[length-1];
            //System.out.println(filename);
            // if dailyData
            boolean daily = false;
            if(filename.matches("\\d+_day.csv")) daily = true;

            String row;
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                Data one;
                if(daily) {
                    one = new DailyData(this.symbol, data[0], data[1], data[2], data[3], data[4], data[5],
                        data[6], data[7], data[8]);
                } else
                    one = new MinutesData(this.symbol, data[0], data[1],data[2],data[3],data[4],data[5],
                        data[6],data[7],data[8],data[9]);
                this.datas.add(one);
            }
            csvReader.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Data> read(String symbol, String date, String time){
        this.symbol = symbol;
        String filepath = this.root + symbol + '\\';
        String[] filenames = new File(filepath).list();
        for(String filename : filenames){
            if(this.read_csv(filepath+filename)) {
            }
            else{
                System.out.println("Read "+filepath+filename+" ERROR!");
            }
            String file_symbol = filename.split("\\.")[0];
            this.all_datas.put(file_symbol, this.datas);
            this.datas = new ArrayList<Data>();
            //return this.all_datas;
        }
        return this.all_datas.get('1');
    }

    public List<Data> GetOneMinutes(){
        return this.all_datas.get("1");
    }
    public List<Data> GetThreeMinutes(){
        return this.all_datas.get("3");
    }
    public List<Data> GetFiveMinutes(){
        return this.all_datas.get("5");
    }
    public List<Data> GetTenMinutes(){
        return this.all_datas.get("10");
    }
    public List<Data> GetHalfHour(){
        return this.all_datas.get("30");
    }
    public List<Data> GetOneHour(){
        return this.all_datas.get("60");
    }
    public List<Data> GetTwoHours(){
        return this.all_datas.get("120");
    }
    public List<Data> GetFiveHours(){
        return this.all_datas.get("300");
    }
    public List<Data> GetOneDay(){
        return this.all_datas.get("1_Day");
    }
    public List<Data> GetThreeDay(){
        return this.all_datas.get("3_Day");
    }
    public List<Data> GetFiveDay(){
        return this.all_datas.get("5_Day");
    }
    public List<Data> GetTenDay(){
        return this.all_datas.get("10_Day");
    }
    public List<Data> GetOneMon(){
        return this.all_datas.get("21_Day");
    }
}
