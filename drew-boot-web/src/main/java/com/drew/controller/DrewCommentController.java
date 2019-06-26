package com.drew.controller;

import com.drew.entity.ResponseResult;
import com.drew.item.dto.ArticleCommentDTO;
import com.drew.service.DrewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户评论相关的controller
 */
@RestController
@RequestMapping("/comment")
public class DrewCommentController {


    @Autowired
    private DrewCommentService drewCommentService;

    @RequestMapping("/addcomment")
    public ResponseResult addComment(@RequestParam("articleId") String articleId,
                                     @RequestParam("comment")String comment){

        try {
            drewCommentService.addDrewComment(articleId,comment);
            return ResponseResult.success;

        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error;
        }
    }

    @RequestMapping("getByArticleId")
    public ResponseResult getByArticleId(@RequestParam("articleId") String articleId){

        try {

            if (StringUtils.isEmpty(articleId)){
                return ResponseResult.success;
            }

            List<ArticleCommentDTO> commentDTOS = drewCommentService.getCommentByArticleId(articleId);

            return new ResponseResult(commentDTOS);
        }catch (Exception e){
            return ResponseResult.error;
        }

    }


}
