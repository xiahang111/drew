package com.drew.controller;

import com.drew.entity.ResponseResult;
import com.drew.item.dto.ArticleBlogDTO;
import com.drew.item.pojo.DrewArticleInfo;
import com.drew.service.DrewArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("drewArticleController")
@RequestMapping("/article")
public class DrewArticleController {


    @Autowired
    private DrewArticleService drewArticleService;

    @RequestMapping("getAll-blog")
    @ResponseBody
    public ResponseResult getAllDrewArticle() {

        try {

            List<ArticleBlogDTO> articleBlogDTOS = drewArticleService.getAllBlogDrewArticleInfo();

            return new ResponseResult(articleBlogDTOS);

        }catch (Exception e){

            e.printStackTrace();
            return new ResponseResult(500,e.getMessage(),null);
        }


    }

    @RequestMapping("addArticle")
    public void addArticle(HttpServletRequest req,
                           HttpServletResponse resp,
                           @RequestParam("title") String title, //文章标题
                           @RequestParam("article-content") String articleContent, //文章内容
                           @RequestParam("keywords") String keywords, //文章关键字
                           @RequestParam("describe") String describe, //文章描述
                           @RequestParam("category") String category, //文章类别
                           @RequestParam("tags") String tags, //文章标签
                           @RequestParam("articlePic") String articlePic, //文章标题图片
                           @RequestParam("visibility") String visibility) { //文章是否公开


        try {

            drewArticleService.addArticle(title,articleContent,"andrew",category,tags,visibility,articlePic);

            resp.sendRedirect("http://localhost:8084/article.html");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
