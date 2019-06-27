package com.drew.controller;

import com.drew.entity.ResponseResult;
import com.drew.item.dto.ArticleBlogDTO;
import com.drew.service.ArticleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleContentController {

    @Autowired
    private ArticleContentService articleContentService;

    @RequestMapping("/{articleId}")
    public String getArticleByArticleId(@PathVariable String articleId,
                                        HttpServletResponse response,
                                        Model model,
                                        HttpServletRequest request) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {

            ArticleBlogDTO articleBlogDTO = articleContentService.getArticleByArticleId(articleId);

            model.addAttribute("articleHeadline",articleBlogDTO.getArticleHeadline());
            response.setHeader("articleId", String.valueOf(articleId));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "article";
    }

    @RequestMapping("/getArticleById")
    @ResponseBody
    public ResponseResult getArticleById(@RequestParam("articleId")String articleId){

        try {

            ArticleBlogDTO articleBlogDTO = articleContentService.getArticleByArticleId(articleId);

            return new ResponseResult(articleBlogDTO);

        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
            return new ResponseResult(500, e.getMessage(), null);
        }

    }

    @RequestMapping("/getArticles")
    @ResponseBody
    public ResponseResult getArticlesByCategoryId(@RequestParam(value = "categoryId",required = false, defaultValue = "") String categoryId,
                                                  @RequestParam(value = "sort",required = false, defaultValue = "")String sort) {

        try {

            List<ArticleBlogDTO> articleBlogDTOS = articleContentService.getArticles(categoryId,sort);

            return new ResponseResult(articleBlogDTOS);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(500, e.getMessage(), null);
        }

    }
    
}
