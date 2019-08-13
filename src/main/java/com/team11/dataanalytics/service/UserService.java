package com.team11.dataanalytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.dataanalytics.dao.UserRepository;
import com.team11.dataanalytics.pojo.User;

import javax.transaction.Transactional;
import java.util.Iterator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean verifyLogin(String username, String password){
        Iterable<User> users = userRepository.findAll();
        int userId = -1;
        for(User user : users){
            if(user.getUserName().equals(username) && user.getPassword().equals(password))
                userId = user.getUserId();
        }
        boolean isPresent = userRepository.existsById(userId);
        return isPresent;
    }

    @Transactional
    public boolean addUser(String newusername, String newuserpassword){
        boolean success = false;
        Iterable<User> users = userRepository.findAll();
        User user= new User();
        user.setUserName(newusername);
        user.setPassword(newuserpassword);
        userRepository.save(user);
        Iterable<User> updatedUsers = userRepository.findAll();
        if(getCollectionSize(updatedUsers) == getCollectionSize(users)+1)
            success = true;
        return success;

    }
    private int getCollectionSize(Iterable<User> collection){
        int count=0;
        Iterator<User> it = collection.iterator();
        while(it.hasNext()){
            it.next();
            count++;
        }
        return count;
    }
}
