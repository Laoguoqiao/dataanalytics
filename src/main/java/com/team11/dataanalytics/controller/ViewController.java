package com.team11.dataanalytics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @RequestMapping(value = "/")
    public String home()
    {

        return "login";
    }
    @RequestMapping(value = "/index")
    public String index()
    {

        return "index";
    }

    @RequestMapping(value = "/table")
    public String table()
    {

        return "tables_dynamic";
    }

    @RequestMapping(value = "/profile")
    public String profile()
    {

        return "profile";
    }
}
