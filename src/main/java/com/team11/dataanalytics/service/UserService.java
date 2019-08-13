package com.team11.dataanalytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.dataanalytics.dao.UserDao;
import com.team11.dataanalytics.pojo.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public boolean verifyLogin(User user){
        List<User> userList = userDao.findByUsernameAndPassword(user.getUserName(), user.getPassword());
        return userList.size()>0;
    }

    @Transactional
    public boolean addUser(User user){
        List<User> userList = userDao.addUser(user.getUserName(),user.getPassword());

    }
}
