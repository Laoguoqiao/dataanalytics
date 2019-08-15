package com.team11.dataanalytics.controller;

import com.alibaba.fastjson.JSON;
import com.team11.dataanalytics.DataAnalyticsApplication;
import com.team11.dataanalytics.domain.User;
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

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DataAnalyticsApplication.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;
    @Before
   public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    public void getUserList() throws Exception {
        String responseString= mockMvc.perform(MockMvcRequestBuilders.get("/api/user/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.contentType(MediaType.APPLICATION_JSON)
                // .content(JSON.toJSON(stock).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void addUser() throws Exception {
        User user=new User();
        user.setAccount("kang");
        user.setPassword("xxx");
        String responseString= mockMvc.perform(MockMvcRequestBuilders.post("/api/user/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                 .content(JSON.toJSON(user).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getUser() throws Exception {
        String responseString= mockMvc.perform(MockMvcRequestBuilders.get("/api/user/users/{id}","4028ab766c933c58016c933c75700000")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSON(user).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void deleteUser() throws Exception {

        String responseString= mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/users/{id}","4028ab766c933c58016c933c75700000")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSON(user).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void updateUser() throws Exception {
        User user=new User();
        user.setPassword("345");
        String responseString= mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/users/{id}","4028ab766c850f14016c850f2b2f0000")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(user).toString())
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void login() throws Exception {
        User user=new User();
        user.setPassword("345");
        user.setAccount("laoguo");
        String responseString= mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSON(user).toString())
        ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    }
    
}