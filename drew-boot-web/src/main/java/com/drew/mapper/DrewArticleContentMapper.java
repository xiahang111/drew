package com.drew.mapper;

import com.drew.item.pojo.DrewArticleContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select article_info_id,article_content,is_deleted from drew_article_content where article_info_id = #{articleInfoId}")
    DrewArticleContent selectByArticleId(@Param("articleInfoId") Long articleInfoId);
}
