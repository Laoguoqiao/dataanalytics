package com.team11.dataanalytics.controller;


import com.team11.dataanalytics.DataAnalyticsApplication;
import io.swagger.annotations.ApiOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class TestDataController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext ).build();

    }
    @Test
    public void getDataWith1Min() throws Exception {
//        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
//                MockMvcRequestBuilders.get("http://127.0.0.1:8081/api/data/data/1/a/2016-3-24");
//        mockHttpServletRequestBuilder.param("symbol", "a");
        


        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/data/data/1/{symbol}/{date}").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED).
                        param("symbol","a").
                        param("date", "2016-3-24")).andExpect(status().isOk()).
                andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);



    }

    @Test
    public void getDataWith3Min() {
    }

    @Test
    public void getDataWith5Min() {
    }
}