package com.team11.dataanalytics.data;

import java.io.*;
import java.util.*;

public class DataProcess {
    // Data of one day stock
    public List<Data> datas = new ArrayList<Data>();

    // All data of one stock
    public Map<String, List<Data>> one_stock = new HashMap<String, List<Data>>();

    // All data
    public Map<String, Map<String, List<Data>>> all_data = new HashMap<String, Map<String, List<Data>>>();//String, List<List<Data>>

    private String symbol;
    private String date;

    public DataProcess(){}

    // Get data of one file, include data of one date and one stock
    public boolean process(String filename){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            int length = filename.split("\\\\").length;
            this.symbol = filename.split("\\\\")[length-1].split("_")[1].split("\\.")[0];
            this.date = filename.split("\\\\")[length-2].split("_")[1];
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                Data one = new Data(data[0], data[1],data[2],data[3],data[4],data[5],
                        data[6],data[7],data[8],data[9]);
                one.setName(symbol);
                datas.add(one);
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
    public boolean getALL(String root){
        // directory of all date
        String[] lists = new File(root).list();
        for(String dir_date : lists){
            // directory of all stock of one date
            String date_dir_path = root + "\\" + dir_date;
            String[] stock_lists = new File(date_dir_path).list();
            for(String stock : stock_lists)
            {
                String filepath = date_dir_path + "\\" +  stock;
                System.out.println(filepath);

                // Get one day data from file
                if (!this.process(filepath)) return false;

                // Get old one_stock from all_data
                if(this.all_data.containsKey(this.symbol))
                    this.one_stock = this.all_data.get(this.symbol);
                else{
                    this.one_stock = new HashMap<String, List<Data>>();
                }
                // Update one_stock
                this.one_stock.put(this.date, this.datas);
                // Update all_data
                this.all_data.put(this.symbol, this.one_stock);
            }
        }

        return true;
    }

}
