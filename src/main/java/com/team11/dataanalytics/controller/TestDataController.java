package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.utils.GetDataUtil;
import com.team11.dataanalytics.utils.Util;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/data")
public class TestDataController {




    @CrossOrigin
    @RequestMapping(value = "/data/1/{symbol}/{date}", method = RequestMethod.GET)
    public Object getDataWith1Min(@PathVariable("symbol") String symbol, @PathVariable("date") String date) throws NotFoundException
    {
        System.out.println("开始分时 股票为："+symbol+"日期为："+date);
        return GetDataUtil.getDataWith1Min(symbol,date);
    }


    @ApiOperation(value="获取股票分钟数据", notes="根据symbol获取股票分钟数据")
    @GetMapping(value = "/data/3/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith3Min(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        return GetDataUtil.getDataWith3Min(symbol);
    }


    @ApiOperation(value="获取股票5分钟数据", notes="根据symbol获取股票5分钟数据")
    @GetMapping(value = "/data/5/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith5Min(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        return GetDataUtil.getDataWith5Min(symbol);
    }

    @CrossOrigin
    @RequestMapping(value = "/data/1day/{symbol}", method = RequestMethod.GET)
    public Object getDataWith1Day(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        System.out.println("开始读取1Day");
        return GetDataUtil.getDataWith1Day(symbol);
    }

    @CrossOrigin
    @ApiOperation(value="获取昨日所有股票数据", notes="获取昨日所有股票数据")
    @GetMapping(value = "/data/stocks")
    @ResponseStatus(HttpStatus.OK)
    public Object getStocksData() throws NotFoundException
    {
        System.out.println("LASTDAY");
        return GetDataUtil.getStockData();
    }

    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }
}
