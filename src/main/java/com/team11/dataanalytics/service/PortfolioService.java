package com.team11.dataanalytics.service;

import com.team11.dataanalytics.dao.PortfolioRepository;
import com.team11.dataanalytics.domain.Portfolio;
import com.team11.dataanalytics.domain.Stock;
import com.team11.dataanalytics.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("portfolioService")
@Transactional
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio addPortfolio(Portfolio portfolio)
    {
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getPortfolioList()
    {
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolio(String id)
    {
        return portfolioRepository.findById(id).get();
    }

    public Portfolio getPortfolioByUid(String uid)
    {
        return portfolioRepository.findByUid(uid);
    }

    public void deletePortfolio(String id)
    {
        portfolioRepository.deleteById(id);
    }

    public Portfolio update(String id, Portfolio portfolio)
    {
        Portfolio currentInstance = portfolioRepository.findById(id).get();

        //支持部分更新
        String[] nullPropertyNames = Util.getNullPropertyNames(portfolio);
        BeanUtils.copyProperties(portfolio, currentInstance, nullPropertyNames);

        return portfolioRepository.save(currentInstance);
    }
}
