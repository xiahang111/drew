package com.drew.service;

import com.drew.item.d_enum.AnonymityUserEnum;
import com.drew.item.d_enum.PlantUserEnum;
import com.drew.item.dto.ArticleDiscussionDTO;
import com.drew.item.pojo.DrewDiscussion;
import com.drew.mapper.DrewDiscussionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrewDiscussionService {

    @Autowired
    private DrewDiscussionMapper drewDiscussionMapper;

    //匿名用户的方法
    public void addDiscussion(String discussion,String parentId){

        AnonymityUserEnum anonymityUserEnum = AnonymityUserEnum.getRandomAnonymityUser();
        PlantUserEnum plantUserEnum = PlantUserEnum.getRandomPlant();

        DrewDiscussion drewDiscussion = new DrewDiscussion(anonymityUserEnum,plantUserEnum);
        drewDiscussion.setDiscussion(discussion);
        if(!StringUtils.isEmpty(parentId)){
            drewDiscussion.setParentId(Long.parseLong(parentId));
        }

        drewDiscussionMapper.insert(drewDiscussion);

    }

    public List<ArticleDiscussionDTO> getAllDiscussion(String sort){

        List<ArticleDiscussionDTO> articleDiscussionDTOS = new ArrayList<>();

        List<DrewDiscussion> drewDiscussions = drewDiscussionMapper.selectAll();

        int floor = 1;
        for (DrewDiscussion drewDiscussion:drewDiscussions) {

            ArticleDiscussionDTO articleDiscussionDTO = new ArticleDiscussionDTO(drewDiscussion);
            articleDiscussionDTO.setFloor(floor);floor++;
            articleDiscussionDTOS.add(articleDiscussionDTO);
        }

        return articleDiscussionDTOS;
    }

    //留给非匿名用户的方法
    public void addDiscussion(String discussion){

    }

}
