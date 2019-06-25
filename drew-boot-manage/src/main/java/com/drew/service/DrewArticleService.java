package com.drew.service;

import com.drew.config.ArticleDataConfig;
import com.drew.item.d_enum.IsDeletedEnum;
import com.drew.item.dto.ArticleBlogDTO;
import com.drew.item.pojo.DrewArticleComment;
import com.drew.item.pojo.DrewArticleContent;
import com.drew.item.pojo.DrewArticleInfo;
import com.drew.mapper.DrewArticleCommentMapper;
import com.drew.mapper.DrewArticleContentMapper;
import com.drew.mapper.DrewArticleInfoMapper;
import com.drew.mapper.DrewCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.DateUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class DrewArticleService {

    @Autowired
    ArticleDataConfig dataConfig;

    @Autowired
    private DrewArticleInfoMapper drewArticleInfoMapper;

    @Autowired
    private DrewArticleContentMapper drewArticleContentMapper;

    @Autowired
    private DrewArticleCommentMapper drewArticleCommentMapper;

    @Autowired
    private DrewCategoryMapper drewCategoryMapper;

    /**
     * 获取所有文章数据并且封装
     * @return
     */
    public List<ArticleBlogDTO> getAllBlogDrewArticleInfo() {

        List<ArticleBlogDTO> articleBlogDTOS = new ArrayList<>();

        //TODO 为了提高响应速度 可以在这里添加缓存 有更改以后再删除缓存
        List<DrewArticleInfo> drewArticleInfos = drewArticleInfoMapper.selectAll();

        List<DrewArticleContent> drewArticleContents = drewArticleContentMapper.selectAll();

        List<DrewArticleComment> drewArticleComments = drewArticleCommentMapper.selectAll();

        if(drewArticleComments == null ){
            drewArticleComments = new ArrayList<>();
        }

        Map<Long, List<DrewArticleContent>> contentMap = drewArticleContents.stream().collect(Collectors.groupingBy(DrewArticleContent::getArticleInfoId));

        Map<Long,List<DrewArticleComment>> commentMap = drewArticleComments.stream().collect(Collectors.groupingBy(DrewArticleComment::getArticleInfoId));

        for (DrewArticleInfo drewArticleInfo:drewArticleInfos) {

            ArticleBlogDTO articleBlogDTO = new ArticleBlogDTO();

            articleBlogDTO.setArticleInfoId(drewArticleInfo.getId());
            articleBlogDTO.setArticleHeadline(drewArticleInfo.getArticleHeadline());
            articleBlogDTO.setArticleVisitor(drewArticleInfo.getArticleVisitor());
            articleBlogDTO.setArticleDate(DateUtils.dateToString(drewArticleInfo.getArticleDate(),DateUtils.DATE_FORMAT));
            articleBlogDTO.setArticleTags(new ArrayList<String>());
            articleBlogDTO.setArticleCategoryId(drewArticleInfo.getArticleCategoryId());
            articleBlogDTO.setArticleCategoryName(drewCategoryMapper.getNameById((long)drewArticleInfo.getArticleCategoryId()));
            articleBlogDTO.setArticleAuthor(drewArticleInfo.getArticleAuthor());
            articleBlogDTO.setArticleImgUrl(drewArticleInfo.getArticleImgUrl());
            articleBlogDTO.setArticleDescription("");


            DrewArticleContent content = contentMap.get(drewArticleInfo.getId()).get(0);
            articleBlogDTO.setArticleContentId(content.getId());
            articleBlogDTO.setArticleContent(content.getArticleContent());

            List<DrewArticleComment> articleComments = commentMap.get(drewArticleInfo.getId());

            if(null == articleComments){
                articleComments = new ArrayList<>();
            }
            List<String> comments = new ArrayList<>();
            for (DrewArticleComment drewArticleComment:articleComments) {

                comments.add(drewArticleComment.getComment());
            }
            articleBlogDTO.setArticleComments(comments);
            articleBlogDTO.setArticleCommentsNum(comments.size());

            articleBlogDTOS.add(articleBlogDTO);

        }

        return articleBlogDTOS;

    }

    public void addArticle(String headLine, String content, String author, String category, String articleTag, String isDeleted,String articlePic) {

        Date now = new Date();

        DrewArticleInfo drewArticleInfo = new DrewArticleInfo();
        drewArticleInfo.setArticleHeadline(headLine);
        drewArticleInfo.setArticleDate(now);
        drewArticleInfo.setArticleAuthor(author);
        drewArticleInfo.setArticleVisitor(0);
        drewArticleInfo.setCreateTime(now);
        drewArticleInfo.setUpdateTime(now);
        drewArticleInfo.setArticleCategoryId(Integer.valueOf(category));
        drewArticleInfo.setArticleImgUrl(articlePic);

        drewArticleInfoMapper.addArticleInfo(drewArticleInfo);

        DrewArticleContent drewArticleContent = new DrewArticleContent();
        drewArticleContent.setArticleContent(content);
        drewArticleContent.setArticleInfoId(drewArticleInfo.getId());
        drewArticleContent.setIsDeleted(IsDeletedEnum.getById(Integer.valueOf(isDeleted)));

        drewArticleContentMapper.addArticleContent(drewArticleContent);


    }
}
