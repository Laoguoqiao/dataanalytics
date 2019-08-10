package com.team11.dataanalytics.util;

import com.team11.dataanalytics.pojo.Data;
import org.junit.Test;

import java.util.List;


public class DataProcessTest {

    @Test
    public void process() {

        DataProcessUtil.process("G:\\TestData\\Quant Quote Market Data - Jan to Mar 2016\\allstocks_20160104\\table_a.csv");
        List<Data> datas=DataProcessUtil.datas;
        System.out.println(datas.size());

    }
}