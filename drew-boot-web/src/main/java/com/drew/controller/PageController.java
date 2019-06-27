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
    public String toSummaryPage(HttpServletResponse response,
                                Model model,
                                HttpServletRequest request){

        response.setHeader("categoryId", "1");
        return "summary";
    }

    @RequestMapping("/data_share")
    public String toDateSharePage(HttpServletResponse response,
                                  Model model,
                                  HttpServletRequest request){

        response.setHeader("categoryId", "2");
        return "data_share";
    }

    @RequestMapping("/readers")
    public String toReader(HttpServletResponse response,
                           Model model,
                           HttpServletRequest request){

        response.setHeader("category", "readers");
        return "readers";
    }

    @RequestMapping("/category")
    public String toCategory(){

        return "category";
    }

    @RequestMapping("/discussion")
    public String todiscussion(){

        return "discussion";
    }


}
