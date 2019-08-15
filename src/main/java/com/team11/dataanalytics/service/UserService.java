package com.team11.dataanalytics.service;

import com.team11.dataanalytics.dao.UserRepository;
import com.team11.dataanalytics.domain.User;
import com.team11.dataanalytics.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    public List<User> getUserList()
    {
        return userRepository.findAll();
    }

    public User getUser(String id)
    {
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id)
    {
        userRepository.deleteById(id);
    }

    public User update(String id, User user)
    {
        User currentInstance = userRepository.findById(id).get();

        //支持部分更新
        String[] nullPropertyNames = Util.getNullPropertyNames(user);
        BeanUtils.copyProperties(user, currentInstance, nullPropertyNames);

        return userRepository.save(currentInstance);
    }

    public boolean verifyUser(User u)
    {
        boolean isSuccess=false;
        String username=u.getAccount();
        String password=u.getPassword();
        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getAccount().equals(username) && user.getPassword().equals(password))
                isSuccess=true;
        }

        return isSuccess;
    }

    public boolean verifyNewUser(User u)
    {
        boolean isSuccess=true;
        String username=u.getAccount();
        String password=u.getPassword();
        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getAccount().equals(username))
                isSuccess=false;
        }

        return isSuccess;
    }

    public Object getUser(String account, String password){
        List<User> users = getUserList();
        for(User user:users){
            if(user.getAccount().equals(account) && user.getPassword().equals(password))
                return user;
        }
        return "No Such User!";
    }

}
