package com.team11.dataanalytics.data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader {
    private String root;
    private String symbol;

    public String[] getSymbols() {
        return symbols;
    }

    private String[] symbols;
    private List<Data> datas = new ArrayList<Data>();
    private Map<String, List<Data>> all_datas = new HashMap<String, List<Data>>();

    public DataReader(String root) {
        this.root = root;
        this.symbols = new File(root).list();
    }

    public List<Data> read_other(String other){
        String filepath = this.root + "OtherData\\" ;
        if(this.read_csv(filepath+other+".csv", true))
            return this.datas;
        else
            return new ArrayList<Data>();
    }

    // Set default value for other
    public boolean read_csv(String filepath){
        return this.read_csv(filepath, false);
    }

    // Read csv and save data into datas
    public boolean read_csv(String filepath, boolean other){
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

                // if other data
                if (other){
                    one = new DailyData(data[0], data[1],data[2],data[3],data[4],data[5],
                            data[6],data[7],data[8],data[9]);
                }
                // if daily data
                else if(daily) {
                    one = new DailyData(this.symbol, data[0], data[1], data[2], data[3], data[4], data[5],
                        data[6], data[7], data[8]);
                }
                // else minutes data
                else
                    one = new MinutesData(this.symbol, data[0], data[1],data[2],data[3],data[4],data[5],
                        data[6],data[7],data[8],data[9]);
                this.datas.add(one);
            }
            csvReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(filepath+" File not found!");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Data> read(String symbol){
        this.symbol = symbol;
        String filepath = this.root + "ProcessData\\" + symbol + '\\';
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
