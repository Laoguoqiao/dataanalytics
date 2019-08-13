package com.team11.dataanalytics.dao;

import org.springframework.data.repository.CrudRepository;
import com.team11.dataanalytics.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
