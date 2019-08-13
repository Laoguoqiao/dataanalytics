package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class LoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String uploadLoginFile() {
        return "login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(String username, String password){

        boolean verify = userService.verifyLogin(username, password);
        if (verify) {
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/addUser")
    public String addUser(String newusername, String newuserpassword){
        boolean success = userService.addUser(newusername, newuserpassword);
        if(success)
            return "redirect:/login";
        else
            return "Add new user error!";
    }

    @RequestMapping("/getData")
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from table_a";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }
        }
        return list;
    }
}