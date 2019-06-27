package com.drew.item.dto;

import com.drew.item.pojo.DrewDiscussion;
import utils.DateUtils;

import java.util.List;

public class ArticleDiscussionDTO {

    private int floor;
    private String nickName;
    private String avatarUrl;
    private String discussion;
    private String address;
    private int praiseNum;
    private String createTime;
    private List<ArticleReplyDTO> articleReplyDTOS;

    public ArticleDiscussionDTO(){}

    public ArticleDiscussionDTO(DrewDiscussion drewDiscussion){
        this.nickName = drewDiscussion.getNickName();
        this.avatarUrl = drewDiscussion.getAvatarUrl();
        this.discussion = drewDiscussion.getDiscussion();
        this.address = drewDiscussion.getAddress();
        this.praiseNum = drewDiscussion.getPraiseNum();
        this.createTime = DateUtils.dateToString(drewDiscussion.getCreateTime(),DateUtils.DATE_TIME_FORMAT);
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
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

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ArticleReplyDTO> getArticleReplyDTOS() {
        return articleReplyDTOS;
    }

    public void setArticleReplyDTOS(List<ArticleReplyDTO> articleReplyDTOS) {
        this.articleReplyDTOS = articleReplyDTOS;
    }
}
