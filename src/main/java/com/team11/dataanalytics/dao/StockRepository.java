package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,String> {
}
