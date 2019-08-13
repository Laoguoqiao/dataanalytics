package com.team11.dataanalytics.controller;

import com.team11.dataanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageDirectController {
    @Autowired
    private UserService userService;

    @GetMapping("toForm_wizards")
    public String gotoForm_Wizards(){
        return "form_wizards";
    }

    @GetMapping("toIndex")
    public String gotoIndex(){
        return "index";
    }

    @GetMapping("toLogin")
    public String gotoLogin(){
        return "login";
    }

    @GetMapping("toProfile")
    public String gotoProfile(){
        return "profile";
    }

    @GetMapping("toDynamicTable")
    public String gotoDynamicTable(){
        return "tables_dynamic";
    }
}
