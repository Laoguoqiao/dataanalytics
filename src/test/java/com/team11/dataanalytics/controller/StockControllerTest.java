package com.team11.dataanalytics.controller;

import com.alibaba.fastjson.JSON;
import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.domain.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DataAnalyticsApplication.class)
@RunWith(SpringRunner.class)
public class StockControllerTest {

    @Autowired
    private StockController stockController;

    private MockMvc mockMvc;
    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(stockController).build();
    }
    @Test
    public void getStockList() throws Exception {

        String responseString= mockMvc.perform(MockMvcRequestBuilders.get("/api/stock/stocks")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

    }

    @Test
    public void addStock() throws Exception {
        Stock stock=new Stock();
        stock.setSymbol("bb");
        String responseString= mockMvc.perform(MockMvcRequestBuilders.post("/api/stock/stocks")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(stock).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getStock() throws Exception {
        String responseString= mockMvc.perform(MockMvcRequestBuilders.get("/api/stock/stocks","11")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.contentType(MediaType.APPLICATION_JSON)
               // .content(JSON.toJSON(stock).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }


    @Test
    public void deleteStock() throws Exception {
        String responseString= mockMvc.perform(MockMvcRequestBuilders.delete("/api/stock/stocks/{id}",1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.contentType(MediaType.APPLICATION_JSON)
                // .content(JSON.toJSON(stock).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();

    }

    @Test
    public void updateStock() {
    }
}