package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.domain.User;
import com.team11.dataanalytics.service.StockService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation(value="获取股票列表", notes="获取股票列表")
    @GetMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> getStockList()
    {
        return stockService.getStockList();
    }

    @ApiOperation(value="添加股票", notes="添加股票")
    @PostMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addStock(@RequestBody Stock stock){
        return stockService.addStock(stock);
    }

    @ApiOperation(value="获取股票信息", notes="根据id获取股票信息")
    @GetMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getStock(@PathVariable("id") String id) throws NotFoundException
    {
        return stockService.getStock(id);
    }

    @ApiOperation(value="删除股票", notes="根据id删除股票")
    @DeleteMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@PathVariable("id") String id)
    {
        stockService.deleteStock(id);
    }

    @ApiOperation(value="更新股票", notes="更新股票")
    @PatchMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Stock updateStock(@PathVariable("id") String id, @RequestBody Stock stock)
    {
        return stockService.update(id,stock);
    }


    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }
}
