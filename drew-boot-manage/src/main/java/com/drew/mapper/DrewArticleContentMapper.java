package com.drew.mapper;

import com.drew.handler.BaseEnumTypeHandler;
import com.drew.item.d_enum.IsDeletedEnum;
import com.drew.item.pojo.DrewArticleContent;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import tk.mybatis.mapper.common.Mapper;

public interface DrewArticleContentMapper extends Mapper<DrewArticleContent> {

//    @ConstructorArgs({
//            @Arg(column = "article_info_id",javaType = Long.class),
//            @Arg(column = "article_content",javaType = String.class),
//            @Arg(column = "is_deleted",javaType = IsDeletedEnum.class,typeHandler = BaseEnumTypeHandler.class)
//    })
    @Insert("INSERT INTO drew_article_content(article_info_id,article_content,is_deleted) VALUES(#{articleInfoId}," +
            "#{articleContent},#{isDeleted})")
    void addArticleContent(DrewArticleContent drewArticleContent);
}
