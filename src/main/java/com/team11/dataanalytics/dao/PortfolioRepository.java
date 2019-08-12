package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,String> {
    Portfolio findByUid(String uid);
}
