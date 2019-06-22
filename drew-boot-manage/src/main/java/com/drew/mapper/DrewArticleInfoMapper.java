package com.drew.mapper;

import com.drew.item.pojo.DrewArticleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface DrewArticleInfoMapper extends Mapper<DrewArticleInfo> {

    @Insert("INSERT INTO drew_article_info(article_headline,article_date,article_visitor,article_tag,article_author,create_time,update_time)" +
            "VALUES(#{articleHeadline},#{articleDate},#{articleVisitor},#{articleTag},#{articleAuthor},#{createTime},#{UpdateTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addArticleInfo(DrewArticleInfo drewArticleInfo);

}
