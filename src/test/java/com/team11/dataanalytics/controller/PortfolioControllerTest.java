package com.team11.dataanalytics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.domain.Portfolio;
import com.team11.dataanalytics.service.PortfolioService;
import org.hibernate.annotations.Target;
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
public class PortfolioControllerTest {

    @Autowired
    private PortfolioController portfolioController;

    @Autowired
    private PortfolioService portfolioService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(portfolioController).build();
    }
    @Test
    public void getPortfolioList() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/portfolio/stocks").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).
                andDo(print()).andReturn().getResponse().getContentAsString();

    }

    @Test
    public void addPortfolio() throws  Exception {
        Portfolio portfolio=new Portfolio();
        portfolio.setStockList("a,aa");
        portfolio.setUid("l");
       String portfiloString= JSON.toJSON(portfolio).toString();
       System.out.println(portfiloString);
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/portfolio/stock").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(portfiloString)
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getPortfolioByUid() throws Exception {

        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/portfolio/stocks/{uid}","1").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();

    }

    @Test
    public void updatePortfolioSymbol() throws Exception {
        Portfolio portfolio=new Portfolio();
        portfolio.setPid("4028ab766c93b619016c93b636830000");
        portfolio.setStockList("a,aa");
        portfolio.setUid("l");
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/portfolio/updateStocks/{symbol}","aa").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSON(portfolio).toString())
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void addToPortfolio() throws Exception{
        Portfolio portfolio=new Portfolio();
        portfolio.setPid("4028ab766c93b619016c93b636830000");
        portfolio.setStockList("a,bbb");
        portfolio.setUid("l");
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/portfolio/addToPortfolio").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSON(portfolio).toString())
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void deletePortfolio() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/portfolio/stocks/{id}","4028ab766c9307be016c9307daf80000").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updatePortfolio() throws Exception {
        Portfolio portfolio=new Portfolio();
        portfolio.setStockList("a,aa,b,D");
        String portfiloString= JSON.toJSON(portfolio).toString();
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/portfolio/stocks/{id}","4028ab766c9309d1016c9309ed160000").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(portfiloString)
        ).andExpect(status().is2xxSuccessful()).
                andDo(print()).andReturn().getResponse().getContentAsString();

    }
}