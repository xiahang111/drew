package com.drew.controller;

import com.drew.entity.ResponseResult;
import com.drew.item.dto.ArticleDiscussionDTO;
import com.drew.item.pojo.DrewDiscussion;
import com.drew.service.DrewDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discussion")
public class DrewDiscussionController {

    @Autowired
    private DrewDiscussionService drewDiscussionService;

    @RequestMapping("/add")
    public ResponseResult addDiscussion(@RequestParam("discussion")String discussion,
                                        @RequestParam(value = "anonymity",required = false, defaultValue = "false")boolean anonymity,
                                        @RequestParam(value = "parentId",required = false, defaultValue = "0") String parentId){

        try {
            drewDiscussionService.addDiscussion(discussion,parentId);

            return ResponseResult.success;
        }catch (Exception e){

            return ResponseResult.error;
        }

    }

    @RequestMapping("/getAll")
    public ResponseResult getAllDiscussion(@RequestParam(value = "sort",required = false, defaultValue = "time")String  sort){

        try {

            List<ArticleDiscussionDTO> drewDiscussions = drewDiscussionService.getAllDiscussion(sort);

            return new ResponseResult(drewDiscussions);
        }catch (Exception e){

            return ResponseResult.error;

        }
    }

}
