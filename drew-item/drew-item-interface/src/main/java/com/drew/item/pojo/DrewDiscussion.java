package com.drew.item.pojo;

import com.drew.item.d_enum.AnonymityUserEnum;
import com.drew.item.d_enum.PlantUserEnum;

import java.util.Date;

public class DrewDiscussion {

    private Long id;
    private Long parentId;
    private String nickName;
    private String avatarUrl;
    private String discussion;
    private String address;
    private int praiseNum;
    private Date createTime;

    public DrewDiscussion(){}

    public DrewDiscussion(AnonymityUserEnum anonymityUserEnum, PlantUserEnum plantUserEnum){
        this.nickName = anonymityUserEnum.getNick_name();
        this.avatarUrl = anonymityUserEnum.getAvatar_url();
        this.address = plantUserEnum.getPlant();
        this.praiseNum = 0;
        this.createTime = new Date();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
