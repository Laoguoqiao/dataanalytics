package com.team11.dataanalytics.utils;

import com.team11.dataanalytics.domain.TestData;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetDataUtilTest {

    @Test
    public void getDataWith1Min() {
        ArrayList<TestData> data = GetDataUtil.getDataWith1Min("target/classes/ProcessedData/a/1.csv");
        System.out.println(data.toString());
    }

    @Test
    public void read() {
        GetDataUtil.read("target/classes/ProcessedData/a/1.csv");
    }

    @Test
    public void readStockData() {
        GetDataUtil.readStockData("target/classes/ProcessedData/lastday.csv");
    }
}