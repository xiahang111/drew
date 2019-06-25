package com.drew.item.pojo;

import com.drew.item.d_enum.IsDeletedEnum;

import javax.persistence.Column;
import javax.persistence.Enumerated;

public class DrewArticleContent {

    private Long id;
    private Long articleInfoId;
    private String articleContent;

    @Enumerated
    private IsDeletedEnum isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleInfoId() {
        return articleInfoId;
    }

    public void setArticleInfoId(Long articleInfoId) {
        this.articleInfoId = articleInfoId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public IsDeletedEnum getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(IsDeletedEnum isDeleted) {
        this.isDeleted = isDeleted;
    }
}
