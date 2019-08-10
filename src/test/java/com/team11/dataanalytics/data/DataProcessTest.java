package com.team11.dataanalytics.data;

import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.common.Constant;
import com.team11.dataanalytics.dao.RedisDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@SpringBootTest(classes = DataAnalyticsApplication.class)
@RunWith(SpringRunner.class)
public class DataProcessTest {

    @Autowired
    private RedisDaoImpl redisDao;
    //装载数据至redis
    @Test
    public void getALL() {
        DataProcess dataProcess = new DataProcess();
        String root = "G:\\TestData\\Quant Quote Market Data - Jan to Mar 2016";
        dataProcess.getALL(root);
        Map<String,Map<String, List<Data>>> allData=dataProcess.all_data;
        for( String key:allData.keySet()){
          redisDao.setHash(Constant.stock_pre+key,allData.get(key));
        }
        System.out.println(dataProcess.all_data.size());
    }
}
