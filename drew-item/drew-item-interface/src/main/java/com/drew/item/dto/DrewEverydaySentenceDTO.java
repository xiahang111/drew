package com.drew.item.dto;

import com.drew.item.pojo.DrewEverdaySentence;
import utils.DateUtils;

import java.util.Date;

public class DrewEverydaySentenceDTO {

    private Long id;
    private String showTime;
    private String createTime;
    private String updateTime;
    private String content;

    public DrewEverydaySentenceDTO(DrewEverdaySentence drewEverdaySentence){

        this.id = drewEverdaySentence.getId();
        this.showTime = DateUtils.dateToString(drewEverdaySentence.getShowTime(),DateUtils.DATE_FORMAT);
        this.createTime = DateUtils.dateToString(drewEverdaySentence.getCreateTime(),DateUtils.DATE_TIME_FORMAT);
        this.updateTime = DateUtils.dateToString(drewEverdaySentence.getUpdateTime(),DateUtils.DATE_TIME_FORMAT);
        this.content = drewEverdaySentence.getContent();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
