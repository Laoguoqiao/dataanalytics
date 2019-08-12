package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,String> {
    Portfolio findByUid(String uid);
}
