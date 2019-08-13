package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<User, Integer> {
}
