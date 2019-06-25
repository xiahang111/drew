package com.drew.item.dto;

import com.drew.item.pojo.DrewArticleComment;
import com.drew.item.pojo.DrewArticleContent;
import com.drew.item.pojo.DrewArticleInfo;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleBlogDTO {

    private Long articleInfoId;
    private Long articleContentId;
    private String articleHeadline;
    private Integer articleCategoryId;
    private String articleCategoryName;
    private String articleAuthor;
    private String articleImgUrl;
    private Integer articleVisitor;
    private List<String> articleComments;
    private Integer articleCommentsNum;
    private String articleContent;
    private String articleDescription;
    private List<String> articleTags;
    private String articleDate;

    public ArticleBlogDTO() {
    }

    public ArticleBlogDTO(DrewArticleInfo drewArticleInfo, DrewArticleContent drewArticleContent, List<DrewArticleComment> drewArticleComments) {

        this.articleInfoId = drewArticleInfo.getId();
        this.articleContentId = drewArticleContent.getId();
        this.articleHeadline = drewArticleInfo.getArticleHeadline();
        this.articleCategoryId = drewArticleInfo.getArticleCategoryId();
        this.articleAuthor = drewArticleInfo.getArticleAuthor();
        this.articleImgUrl = drewArticleInfo.getArticleImgUrl();
        this.articleVisitor = drewArticleInfo.getArticleVisitor();

        if (null == articleComments) {
            articleComments = new ArrayList<>();
        }
        List<String> comments = new ArrayList<>();
        for (DrewArticleComment drewArticleComment : drewArticleComments) {

            comments.add(drewArticleComment.getComment());
        }
        this.articleComments = comments;
        this.articleCommentsNum = articleComments.size();
        this.articleContent = drewArticleContent.getArticleContent();
        this.articleDate = DateUtils.dateToString(drewArticleInfo.getArticleDate(), DateUtils.DATE_FORMAT);
        this.articleTags = new ArrayList<>();
        this.articleDescription = "";
    }

    public Long getArticleInfoId() {
        return articleInfoId;
    }

    public void setArticleInfoId(Long articleInfoId) {
        this.articleInfoId = articleInfoId;
    }

    public Long getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Long articleContentId) {
        this.articleContentId = articleContentId;
    }

    public String getArticleHeadline() {
        return articleHeadline;
    }

    public void setArticleHeadline(String articleHeadline) {
        this.articleHeadline = articleHeadline;
    }


    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl;
    }

    public Integer getArticleVisitor() {
        return articleVisitor;
    }

    public void setArticleVisitor(Integer articleVisitor) {
        this.articleVisitor = articleVisitor;
    }

    public List<String> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(List<String> articleComments) {
        this.articleComments = articleComments;
    }

    public Integer getArticleCommentsNum() {
        return articleCommentsNum;
    }

    public void setArticleCommentsNum(Integer articleCommentsNum) {
        this.articleCommentsNum = articleCommentsNum;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public List<String> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(List<String> articleTags) {
        this.articleTags = articleTags;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }
}
