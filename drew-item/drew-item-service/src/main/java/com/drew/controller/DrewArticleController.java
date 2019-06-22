package com.drew.controller;

import com.drew.entity.ResponseResult;
import com.drew.item.pojo.DrewArticleInfo;
import com.drew.service.DrewArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class DrewArticleController {


    @Autowired
    private DrewArticleService drewArticleService;

    @RequestMapping("getAll")
    public ResponseResult getAllDrewArticle(){

        List<DrewArticleInfo> drewArticleInfos = drewArticleService.getAllDrewArticleInfo();

        return new ResponseResult(drewArticleInfos);

    }

    @RequestMapping("addArticle")
    public ResponseResult addArticle(@RequestParam("title")String title,@RequestParam("article-content")String articleContent,
                                     @RequestParam("category")String category){


        Map<String,Object> map = new HashMap<>();

        map.put("title",title);
        map.put("article-content",articleContent);
        map.put("category",category);

        return new ResponseResult(map);

    }
}
