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

import java.util.Collections;
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
        String root = "G:\\DataProcessTest";
        dataProcess.getALL(root);
        Map<String,Map<String, List<Data>>> allData=dataProcess.all_data;
        for( String key:allData.keySet()){
            for(String hashKey:allData.get(key).keySet()) {
                redisDao.setList(Constant.stock_pre+key+":" + hashKey, Collections.singletonList(allData.get(key).get(hashKey)));
            }
        }
        System.out.println(dataProcess.all_data.size());
    }

    @Test
    public void getData(){

        List<Data> list= (List<Data>) redisDao.getHash(Constant.stock_pre+'a',"20160104");
        System.out.println(list.size());
    }
}
