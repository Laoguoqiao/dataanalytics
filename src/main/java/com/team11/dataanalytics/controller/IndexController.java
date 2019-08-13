package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.devkit.FileProcessor;
import com.team11.dataanalytics.devkit.JsonUtils;
import com.team11.dataanalytics.pojo.StockData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/indexController")
public class IndexController {


    @ResponseBody
    @RequestMapping(value = "/getStockData")
    public String getStockData(){
//        System.out.println("readData");
        return JsonUtils.obj2String(FileProcessor.readCSV("target/classes/data/1_day.csv"));
    }


}
