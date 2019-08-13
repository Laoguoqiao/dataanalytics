package com.team11.dataanalytics.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.openfeign.client.PythonClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class DataReaderTest {
    private String root = "ProcessedData/";
    private DataReader dataReader;

    @Autowired
    private PythonClient pythonClient;

    @Before
    public void setUp(){
        this.dataReader = new DataReader(this.root);
    }

    @Test
    public void pythonclient(){
        String result = pythonClient.pythonGetDataBySymbolSlice("a","d");
        Map data = (Map) JSONObject.parse(result);
        System.out.println(data.get("Open"));
    }

    @Test
    public void checkPath(){
        String path ="target/classes/ProcessedData/" + "a" +"/";
        String[] filenames = new File(path).list();
        System.out.println(filenames[0]);
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
        Assert.assertEquals(609, other.size());
    }
    @Test
    public void read_other_failed(){
        List<Data> other = this.dataReader.read_other("notExist");
        Assert.assertEquals(0,other.size());
    }
}
