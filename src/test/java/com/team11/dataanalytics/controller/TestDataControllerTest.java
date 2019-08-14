package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.DataanalyticsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = DataanalyticsApplication.class)
@RunWith(SpringRunner.class)
public class TestDataControllerTest {

    @Autowired
    private  UserController userController;
    @Test
    public void getDataWith1Min() {
    }

    @Test
    public void getDataWith3Min() {
    }

    @Test
    public void getDataWith5Min() {
    }
}