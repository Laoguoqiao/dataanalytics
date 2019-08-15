package com.team11.dataanalytics.service;

import com.team11.dataanalytics.dao.PortfolioRepository;
import com.team11.dataanalytics.domain.Portfolio;
import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.utils.Util;
import javassist.NotFoundException;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("portfolioService")
@Transactional
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;


    public String addPortfolio(Portfolio portfolio) throws ObjectDeletedException {
        StringBuilder result = new StringBuilder();
        boolean exists = false;
        List<Portfolio> portfolioList = this.getPortfolioList();
        for (Portfolio p : portfolioList) {
            if (portfolio.getUid().equals(p.getUid())) {

                List<String> stock_list = Arrays.asList(p.getStockList().split(","));
                for (String symbol : stock_list) {
                    if (portfolio.getStockList().equals(symbol)) {
                        result.append("This stock is already in your portfolio!");
                        exists = true;
                        break;
                    }
                }
                if (!exists) {

                    StringBuilder existsStocks = new StringBuilder(p.getStockList());
                    existsStocks.append(",");
                    existsStocks.append(portfolio.getStockList());

                    p.setStockList(existsStocks.toString());
                    this.update(p.getPid(), p);
//                    portfolioRepository.save(p);
                    result.append("Add Stock To Portfolio Success!");
                    exists = true;
                    break;
                }

            }
        }
        if (!exists) {
            result.append("Add Stock To Portfolio Success!");
            portfolioRepository.save(portfolio);
        }

        return result.toString();
    }

    public List<Portfolio> getPortfolioList() {
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolio(String id) {
        return portfolioRepository.findById(id).get();
    }

    public Portfolio getPortfolioByUid(String uid) throws NotFoundException {
        return portfolioRepository.findByUid(uid);
    }


    public void deletePortfolio(String id) {

        portfolioRepository.deleteById(id);
    }


    public Portfolio update(String id, Portfolio portfolio) {
        Portfolio currentInstance = portfolioRepository.findById(id).get();

        //支持部分更新
        String[] nullPropertyNames = Util.getNullPropertyNames(portfolio);
        BeanUtils.copyProperties(portfolio, currentInstance, nullPropertyNames);

        return portfolioRepository.saveAndFlush(currentInstance);
    }

    public String updatePortfolioSymbol(String symbol, Portfolio portfolio) throws NotFoundException {
        StringBuilder result = new StringBuilder();
        Portfolio currentInstance = this.getPortfolioByUid(portfolio.getUid());
        List<String> stocks = Arrays.asList(currentInstance.getStockList().split(","));
        stocks = new ArrayList<>(stocks);

        System.out.println("-------------------------"+stocks.size());
        for(int i = stocks.size()-1; i >= 0; i--){
            if(stocks.get(i).equals(symbol)){
                if(stocks.size() != 1){
                    stocks.remove(i);
                    currentInstance.updateStockList(stocks);
                    this.update(currentInstance.getPid(),currentInstance);

                    result.append("delete success!");
                    break;
                }
                else{
                    this.deletePortfolio(currentInstance.getPid());
                    result.append("delete success!");
                    break;
                }
            }
        }
        return result.toString();
    }
}
