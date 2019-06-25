package com.drew.item.pojo;

import com.drew.item.d_enum.IsDeletedEnum;

import java.util.Date;

public class DrewArticleComment {

    private Long id;
    private Long articleInfoId;
    private IsDeletedEnum isDeletedEnum;
    private String comment;
    private Date createTime;
    private Date updateTime;

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

    public IsDeletedEnum getIsDeletedEnum() {
        return isDeletedEnum;
    }

    public void setIsDeletedEnum(IsDeletedEnum isDeletedEnum) {
        this.isDeletedEnum = isDeletedEnum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
