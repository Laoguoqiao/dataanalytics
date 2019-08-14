package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.DataAnalyticsApplication;
import io.swagger.annotations.ApiOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class TestDataControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext ).build();

    }
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