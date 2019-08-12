package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.utils.GetDataUtil;
import com.team11.dataanalytics.utils.Util;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class TestDataController {

    @ApiOperation(value="获取股票分钟数据", notes="根据symbol获取股票分钟数据")
    @GetMapping(value = "/data/1/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith1Min(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        return GetDataUtil.getDataWith1Min(symbol);
    }

    @ApiOperation(value="获取股票分钟数据", notes="根据symbol获取股票分钟数据")
    @GetMapping(value = "/data/3/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith3Min(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        return GetDataUtil.getDataWith3Min(symbol);
    }

    @ApiOperation(value="获取股票分钟数据", notes="根据symbol获取股票分钟数据")
    @GetMapping(value = "/data/5/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    public Object getDataWith5Min(@PathVariable("symbol") String symbol) throws NotFoundException
    {
        return GetDataUtil.getDataWith5Min(symbol);
    }

    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }
}
