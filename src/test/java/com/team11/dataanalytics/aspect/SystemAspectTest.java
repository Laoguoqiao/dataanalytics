package com.team11.dataanalytics.aspect;

import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.controller.UserController;
import com.team11.dataanalytics.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = DataAnalyticsApplication.class)
@RunWith(SpringRunner.class)
public class SystemAspectTest {

    @Autowired
    private UserController userController;

    @Test
    public void saveLog() {

        User user=new User();
        user.setAccount("laoguo");
        user.setPassword("123");
        userController.addUser(user);

    }
}