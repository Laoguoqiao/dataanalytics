package com.team11.dataanalytics.service;

import com.team11.dataanalytics.dao.StockRepository;
import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.domain.User;
import com.team11.dataanalytics.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("stockService")
@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock addStock(Stock stock)
    {
        return stockRepository.save(stock);
    }

    public List<Stock> getStockList()
    {
        return stockRepository.findAll();
    }

    public Stock getStock(String id)
    {
        return stockRepository.findById(id).get();
    }

    public void deleteStock(String id)
    {
        stockRepository.deleteById(id);
    }

    public Stock update(String id, Stock stock)
    {
        Stock currentInstance = stockRepository.findById(id).get();

        //支持部分更新
        String[] nullPropertyNames = Util.getNullPropertyNames(stock);
        BeanUtils.copyProperties(stock, currentInstance, nullPropertyNames);

        return stockRepository.save(currentInstance);
    }
}
