package com.drew.service;

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
import org.springframework.util.StringUtils;
import utils.DateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleContentService {

    @Autowired
    private DrewArticleInfoMapper drewArticleInfoMapper;

    @Autowired
    private DrewArticleContentMapper drewArticleContentMapper;

    @Autowired
    private DrewArticleCommentMapper drewArticleCommentMapper;

    @Autowired
    private DrewCategoryMapper drewCategoryMapper;

    public ArticleBlogDTO getArticleByArticleId(String articleId) {

        DrewArticleInfo drewArticleInfo = drewArticleInfoMapper.selectByArticleId(Long.parseLong(articleId));

        DrewArticleContent drewArticleContent = drewArticleContentMapper.selectByArticleId(Long.parseLong(articleId));

        List<DrewArticleComment> drewArticleComments = drewArticleCommentMapper.selectByArticleInfoId(Long.parseLong(articleId));

        ArticleBlogDTO articleBlogDTO = new ArticleBlogDTO(drewArticleInfo, drewArticleContent, drewArticleComments);

        articleBlogDTO.setArticleCategoryName(drewCategoryMapper.getNameById((long) drewArticleInfo.getArticleCategoryId()));

        return articleBlogDTO;

    }

    public List<ArticleBlogDTO> getArticles(String categoryId, String sort) {

        List<ArticleBlogDTO> articleBlogDTOS = new ArrayList<>();

        List<DrewArticleInfo> drewArticleInfos;

        if (StringUtils.isEmpty(categoryId)) {
            drewArticleInfos = drewArticleInfoMapper.selectAll();
        } else {
            drewArticleInfos = drewArticleInfoMapper.selectByCategoryId(Integer.parseInt(categoryId));

        }

        List<DrewArticleContent> drewArticleContents = drewArticleContentMapper.selectAll();

        List<DrewArticleComment> drewArticleComments = drewArticleCommentMapper.selectAll();

        if (drewArticleComments == null) {
            drewArticleComments = new ArrayList<>();
        }

        Map<Long, List<DrewArticleContent>> contentMap = drewArticleContents.stream().collect(Collectors.groupingBy(DrewArticleContent::getArticleInfoId));

        Map<Long, List<DrewArticleComment>> commentMap = drewArticleComments.stream().collect(Collectors.groupingBy(DrewArticleComment::getArticleInfoId));

        for (DrewArticleInfo drewArticleInfo : drewArticleInfos) {

            ArticleBlogDTO articleBlogDTO = new ArticleBlogDTO();

            articleBlogDTO.setArticleInfoId(drewArticleInfo.getId());
            articleBlogDTO.setArticleHeadline(drewArticleInfo.getArticleHeadline());
            articleBlogDTO.setArticleVisitor(drewArticleInfo.getArticleVisitor());
            articleBlogDTO.setArticleDate(DateUtils.dateToString(drewArticleInfo.getArticleDate(), DateUtils.DATE_FORMAT));
            articleBlogDTO.setArticleTags(new ArrayList<String>());
            articleBlogDTO.setArticleCategoryId(drewArticleInfo.getArticleCategoryId());
            articleBlogDTO.setArticleCategoryName(drewCategoryMapper.getNameById((long) drewArticleInfo.getArticleCategoryId()));
            articleBlogDTO.setArticleAuthor(drewArticleInfo.getArticleAuthor());
            articleBlogDTO.setArticleImgUrl(drewArticleInfo.getArticleImgUrl());
            articleBlogDTO.setArticleDescription("");


            DrewArticleContent content = contentMap.get(drewArticleInfo.getId()).get(0);
            articleBlogDTO.setArticleContentId(content.getId());
            articleBlogDTO.setArticleContent(content.getArticleContent());

            List<DrewArticleComment> articleComments = commentMap.get(drewArticleInfo.getId());

            if (null == articleComments) {
                articleComments = new ArrayList<>();
            }
            List<String> comments = new ArrayList<>();
            for (DrewArticleComment drewArticleComment : articleComments) {

                comments.add(drewArticleComment.getComment());
            }
            articleBlogDTO.setArticleComments(comments);
            articleBlogDTO.setArticleCommentsNum(comments.size());

            articleBlogDTOS.add(articleBlogDTO);

        }

        switch (sort) {

            //将结果通过时间排序
            case "time":
                Collections.sort(articleBlogDTOS, new Comparator<ArticleBlogDTO>() {
                    @Override
                    public int compare(ArticleBlogDTO o1, ArticleBlogDTO o2) {
                        Date a = DateUtils.stringToDate(o1.getArticleDate(), DateUtils.DATE_TIME_FORMAT);
                        Date b = DateUtils.stringToDate(o2.getArticleDate(), DateUtils.DATE_TIME_FORMAT);

                        if (a.after(b)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });

                break;

            case "visitor":
                Collections.sort(articleBlogDTOS, new Comparator<ArticleBlogDTO>() {
                    @Override
                    public int compare(ArticleBlogDTO o1, ArticleBlogDTO o2) {
                        return o1.getArticleVisitor() > o2.getArticleVisitor() ? 1 : -1;
                    }
                });

                break;
            default:
                break;
        }

        return articleBlogDTOS;

    }

}
