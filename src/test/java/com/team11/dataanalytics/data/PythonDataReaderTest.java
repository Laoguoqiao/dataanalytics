package com.team11.dataanalytics.data;

import com.team11.dataanalytics.DataAnalyticsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class PythonDataReaderTest {
    @Autowired
    private  PythonDataReader pythonDataReader;

    @Test
    public void getDataBySymbol() {
        for(Data data :this.pythonDataReader.GetDataBySymbol("a")){
            System.out.println(data.toString());
        }
    }

    @Test
    public void getYahooData(){
        for(Data data :this.pythonDataReader.GetYahooData("a","2016-01-04","2016-01-05")){
            System.out.println(data.toString());
        }
    }
}