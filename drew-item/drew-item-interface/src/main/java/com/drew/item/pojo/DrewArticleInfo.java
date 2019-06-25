package com.drew.item.pojo;

import javax.persistence.Column;
import java.util.Date;

public class DrewArticleInfo {

    private Long id;
    private String articleHeadline; //文章标题
    private Date articleDate; //文章生成时间
    private Integer articleVisitor; //文章浏览人数
    private String articleTag; //文章标签
    private String articleAuthor; //文章作者

    @Column(name = "article_category_id")
    private Integer articleCategoryId;

    private String articleImgUrl;
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleHeadline() {
        return articleHeadline;
    }

    public void setArticleHeadline(String articleHeadline) {
        this.articleHeadline = articleHeadline;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public Integer getArticleVisitor() {
        return articleVisitor;
    }

    public void setArticleVisitor(Integer articleVisitor) {
        this.articleVisitor = articleVisitor;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public int getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(int articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl;


    }


}
