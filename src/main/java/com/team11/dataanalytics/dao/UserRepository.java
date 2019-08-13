package com.team11.dataanalytics.dao;

import org.springframework.data.repository.CrudRepository;
import com.team11.dataanalytics.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    public List<User> findByUsernameAndPassword(String name, String password);
    public List<User> addUser(String name, String password);
}
