package com.drew.item.pojo;

public class DrewCategory {

    private long id;
    private String categoryName;
    private long categoryParentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }
}
