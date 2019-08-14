package com.team11.dataanalytics.data;

import com.team11.dataanalytics.DataAnalyticsApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Data> datalist = this.pythonDataReader.GetYahooData("a","2016-01-04","2016-01-05");
        Assert.assertEquals(2, datalist.size());
//        for(Data data : datalist){
//            System.out.println(data.toString());
//        }
    }

    @Test
    public void getOriginData(){
        List<Data> dataList = this.pythonDataReader.GetOriginData("aapl","2016-02-14", "2016-02-15");
        Assert.assertEquals(2, dataList.size());
    }

    @Test
    public void GetDataBySymbolSlice(){
        List<Data> dataList = this.pythonDataReader.GetDataBySymbolSlice("aapl", "day","1",
                "2016-02-14", "2016-02-15");
        Assert.assertEquals(2, dataList.size());
        Assert.assertEquals("2016-02-14", ((DailyData)dataList.get(0)).getDate());
        Assert.assertEquals("2016-02-15", ((DailyData)dataList.get(1)).getDate());
    }

}