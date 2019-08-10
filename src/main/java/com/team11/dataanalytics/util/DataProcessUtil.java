package com.team11.dataanalytics.util;

import com.team11.dataanalytics.pojo.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProcessUtil {

    public  static List<Data> datas = new ArrayList<Data>();

    public static boolean process(String filename){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            int length = filename.split("\\\\").length;
            String symbol = filename.split("\\\\")[length-1].split("_")[1].split("\\.")[0];
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
