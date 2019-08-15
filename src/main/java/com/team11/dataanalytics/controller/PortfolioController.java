package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.domain.Portfolio;
import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.service.PortfolioService;
import com.team11.dataanalytics.service.StockService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Port;
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

    @ApiOperation(value="添加投资组合", notes="添加投资组合")
    @PostMapping(value = "/stocks")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addPortfolio(@RequestBody Portfolio portfolio){
        return portfolioService.addPortfolio(portfolio);
    }

    @ApiOperation(value="获取投资组合信息", notes="根据uid获取投资组合信息")
    @GetMapping(value = "/getStockByUID/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public Object getPortfolioByUid(@PathVariable("uid") String uid) throws NotFoundException
    {
        return portfolioService.getPortfolioByUid(uid);
    }

    @ApiOperation(value="删除投资组合", notes="根据id删除投资组合")
    @DeleteMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePortfolio(@PathVariable("id") String id)
    {
        portfolioService.deletePortfolio(id);
    }

    @ApiOperation(value="更新投资组合", notes="更新投资组合")
    @PatchMapping(value = "/stocks/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio updatePortfolio(@PathVariable("id") String id, @RequestBody Portfolio portfolio)
    {
        return portfolioService.update(id,portfolio);
    }

    @ApiOperation(value="更新投资组合中的股票", notes="更新投资组合股票")
    @RequestMapping(value = "/updateStocks/{symbol}", method=RequestMethod.POST)
    public Object updatePortfolioSymbol(@PathVariable("symbol") String symbol, @RequestBody Portfolio portfolio) throws NotFoundException {
        System.out.println("=====================================");
        return portfolioService.updatePortfolioSymbol(symbol,portfolio);
    }


    @ApiOperation(value="测试")
    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test()
    {
        return "test ok!";
    }

    @RequestMapping(value="/addToPortfolio", method=RequestMethod.POST)
    public Object addToPortfolio(@RequestBody Portfolio portfolio, HttpServletRequest request) throws ObjectDeletedException,IllegalArgumentException,InvalidDataAccessApiUsageException {
        String verify = portfolioService.addPortfolio(portfolio);

        return verify;
    }

}
