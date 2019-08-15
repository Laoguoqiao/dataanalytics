package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.utils.GetDataUtil;
import com.team11.dataanalytics.utils.Util;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private  GetDataUtil getDataUtil;


    @CrossOrigin
    @RequestMapping(value = "/data/1/{symbol}/{date}", method = RequestMethod.GET)
    public Object getDataWith1Min(@PathVariable("symbol") String symbol, @PathVariable("date") String date) throws NotFoundException
    {
        System.out.println("开始分时 股票为："+symbol+"日期为："+date);
        return getDataUtil.getDataWith1Min(symbol,date);
    }


    @ApiOperation(value="获取股票分钟数据", notes="根据symbol获取股票分钟数据")
    @GetMapping(value = "/data/3/{symbol}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith3Min(@PathVariable("symbol") String symbol,@PathVariable("date")String date) throws NotFoundException
    {
        return getDataUtil.getDataWith3Min(symbol,date);
    }


    @ApiOperation(value="获取股票5分钟数据", notes="根据symbol获取股票5分钟数据")
    @GetMapping(value = "/data/5/{symbol}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith5Min(@PathVariable("symbol") String symbol,@PathVariable("date")String date) throws NotFoundException
    {
        return getDataUtil.getDataWith5Min(symbol,date);
    }

    @ApiOperation(value="获取股票15分钟数据", notes="根据symbol获取股票15分钟数据")
    @GetMapping(value = "/data/15/{symbol}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith15Min(@PathVariable("symbol") String symbol,@PathVariable("date")String date) throws NotFoundException
    {
        return getDataUtil.getDataWith15Min(symbol,date);
    }

    @ApiOperation(value="获取股票30分钟数据", notes="根据symbol获取股票30分钟数据")
    @GetMapping(value = "/data/30/{symbol}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith30Min(@PathVariable("symbol") String symbol,@PathVariable("date")String date) throws NotFoundException
    {
        return getDataUtil.getDataWith30Min(symbol,date);
    }

    @ApiOperation(value="获取股票60分钟数据", notes="根据symbol获取股票60分钟数据")
    @GetMapping(value = "/data/60/{symbol}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith60Min(@PathVariable("symbol") String symbol,@PathVariable("date")String date) throws NotFoundException
    {
        return getDataUtil.getDataWith60Min(symbol,date);
    }

    @CrossOrigin
    @ApiOperation(value = "获取一天的数据")
    @RequestMapping(value = "/data/1day/{symbol}", method = RequestMethod.GET)
    public Object getDataWith1Day(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        System.out.println("开始读取1Day");
        return getDataUtil.getDataWith1Day(symbol);
    }

    @CrossOrigin
    @ApiOperation(value="获取昨日所有股票数据", notes="获取昨日所有股票数据")
    @GetMapping(value = "/data/stocks")
    @ResponseStatus(HttpStatus.OK)
    public Object getStocksData() throws NotFoundException
    {
        System.out.println("LASTDAY");
        return getDataUtil.getLastDay();
    }

    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }
}
