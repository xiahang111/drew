package com.drew.mapper;

import com.drew.item.pojo.DrewArticleContent;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface DrewArticleContentMapper extends Mapper<DrewArticleContent> {

    @Insert("INSERT INTI drew_article_content(article_info_id,article_content,is_deleted) VALUES(#{},#{})")
    void addArticleContent(DrewArticleContent drewArticleContent);
}
