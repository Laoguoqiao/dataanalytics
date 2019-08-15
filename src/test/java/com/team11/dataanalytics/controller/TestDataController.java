package com.team11.dataanalytics.controller;


import com.team11.dataanalytics.DataAnalyticsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyticsApplication.class)
public class TestDataController {

    @Autowired
    private DataController dataController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(dataController).build();
    }
    @Test
    public void getDataWith1Min() throws Exception { ;
        


        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/data/data/1/{symbol}/{date}","a","2016-3-24").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        ).andExpect(status().isOk()).
                andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);



    }

    @Test
    public void getDataWith3Min() throws Exception {

        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/data/data/3/{symbol}/{date}","a","2016-3-24").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).
                andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void getDataWith5Min() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/data/data/5/{symbol}/{date}","a","2016-3-24").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).
                andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}