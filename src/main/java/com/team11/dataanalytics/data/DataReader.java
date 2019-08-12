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

    public Map<String, List<Data>> read(String symbol, String date, String time){
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
        return this.all_datas;
    }
}
