package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.pojo.User;
import javafx.beans.binding.ObjectExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class RedisDaoImplTest {

    @Autowired
    private RedisDaoImpl redisDao;

    @Test
    public void setObject() {
        User user=new User();
        user.setUserId(1);
        user.setUserName("laoguo");
        redisDao.setObject("user1",user);

    }

    @Test
    public void getObject() {

        User user= (User) redisDao.getObject("user1");
        System.out.println(user.toString());
        //key :Map<Long,List<Object>

    }

    @Test
    public void delObject() {
    }

    @Test
    public void setList() {
      Map<String,List<User>> map=new HashMap<>();
      User user1=new User();
      user1.setUserId(111);
      user1.setUserName("laguo");
      User user2=new User();
      user2.setUserId(222);
      user2.setUserName("kkk");
      List<User> list=new ArrayList<>();
      list.add(user1);
      list.add(user2);
      map.put("list1",list);
    redisDao.setHash("hash1",map);

    }

    @Test
    public void getList() {

        List<User> list= (List<User>) redisDao.getHash("hash1","list1");

        System.out.println(list.get(0).toString());
    }
}