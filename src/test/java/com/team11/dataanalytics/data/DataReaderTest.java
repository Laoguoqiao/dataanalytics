package com.team11.dataanalytics.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DataReaderTest {
    private String root = "C:\\Users\\宝是宝藏的宝\\Desktop\\learning\\Trading_Strategy\\DataAnalytics\\Data Analytics\\";
    private DataReader dataReader;

    @Before
    public void setUp(){
        this.dataReader = new DataReader(this.root);
    }

    @Test
    public void read() {
        List<Data> datas = this.dataReader.read("a");
        System.out.println(datas);
    }

    @Test
    public void constractor(){
        Assert.assertEquals(this.dataReader.getSymbols().length, 609);
    }

    @Test
    public void read_other(){
        List<Data> other = this.dataReader.read_other("lastday");
        Assert.assertEquals(other.size(), 609);
    }
}
