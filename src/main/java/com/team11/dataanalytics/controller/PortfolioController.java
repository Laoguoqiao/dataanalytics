package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.annotation.SystemLog;
import com.team11.dataanalytics.domain.Portfolio;
import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.service.PortfolioService;
import com.team11.dataanalytics.service.StockService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @ApiOperation(value="获取投资组合列表", notes="获取投资组合列表")
    @GetMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.OK)
    public List<Portfolio> getPortfolioList()
    {
        return portfolioService.getPortfolioList();
    }

    @SystemLog("添加投资组合")
    @ApiOperation(value="添加投资组合", notes="添加投资组合")
    @PostMapping(value = "/stock")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addPortfolio(@RequestBody Portfolio portfolio){
        return portfolioService.addPortfolio(portfolio);
    }

    @ApiOperation(value="获取投资组合信息", notes="根据uid获取投资组合信息")
    @GetMapping(value = "/stocks/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPortfolioByUid(@PathVariable("uid") String uid) throws NotFoundException
    {
        return portfolioService.getPortfolioByUid(uid);
    }

    @SystemLog("删除投资组合")
    @ApiOperation(value="删除投资组合", notes="根据id删除投资组合")
    @DeleteMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePortfolio(@PathVariable("id") String id)
    {
        portfolioService.deletePortfolio(id);
    }

    @SystemLog("更新投资组合")
    @ApiOperation(value="更新投资组合", notes="更新投资组合")
    @PatchMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio updatePortfolio(@PathVariable("id") String id, @RequestBody Portfolio portfolio)
    {
        return portfolioService.update(id,portfolio);
    }


    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }
}
