package com.drew.item.d_enum;

public enum IsDeletedEnum implements BaseEnum {

    CONFIRM_DELETED(0, "已删除"),
    NOT_DELETED(1, "未删除");

    private int value;
    private String description;

    IsDeletedEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static IsDeletedEnum getById(int value) {

        for (IsDeletedEnum en : IsDeletedEnum.values()) {

            if (en.getValue() == value) {
                return en;
            }
        }

        return null;

    }
}
