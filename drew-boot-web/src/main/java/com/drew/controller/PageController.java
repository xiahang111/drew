package com.drew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String toIndexPage(){

        return "index";
    }

    @RequestMapping("/summary")
    public String toSummaryPage(){

        return "summary";
    }

    @RequestMapping("/data_share")
    public String toDateSharePage(){

        return "data_share";
    }

    @RequestMapping("/readers")
    public String toReader(){

        return "readers";
    }

    @RequestMapping("/category")
    public String toCategory(){

        return "category";
    }


}
