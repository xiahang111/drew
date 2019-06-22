package com.drew.service;

import com.drew.config.ArticleDataConfig;
import com.drew.item.d_enum.IsDeletedEnum;
import com.drew.item.pojo.DrewArticleContent;
import com.drew.item.pojo.DrewArticleInfo;
import com.drew.mapper.DrewArticleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.DateUtils;


import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DrewArticleService {

    @Autowired
    ArticleDataConfig dataConfig;

    @Autowired
    private DrewArticleInfoMapper drewArticleInfoMapper;

    public List<DrewArticleInfo> getAllDrewArticleInfo(){
        return drewArticleInfoMapper.selectAll();
    }

    public void addArticle(String headLine,String content,String author,String category,String articleTag,String isDeleted){

        Date now = new Date();

        DrewArticleInfo drewArticleInfo = new DrewArticleInfo();
        drewArticleInfo.setArticleHeadline(headLine);
        drewArticleInfo.setArticleDate(now);
        drewArticleInfo.setArticleAuthor(author);
        drewArticleInfo.setArticleVisitor(0);
        drewArticleInfo.setCreateTime(now);
        drewArticleInfo.setUpdateTime(now);
        drewArticleInfo.setArticleCategoryId(Integer.valueOf(category));

        drewArticleInfoMapper.addArticleInfo(drewArticleInfo);

        DrewArticleContent drewArticleContent = new DrewArticleContent();
        drewArticleContent.setArticleContent(content);
        drewArticleContent.setArticleInfoId(drewArticleInfo.getId());
        drewArticleContent.setIsDeleted(IsDeletedEnum.getById(Integer.valueOf(isDeleted)));



    }
}
