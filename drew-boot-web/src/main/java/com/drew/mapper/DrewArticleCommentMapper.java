package com.drew.mapper;

import com.drew.item.pojo.DrewArticleComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrewArticleCommentMapper extends Mapper<DrewArticleComment> {


    @Select("SELECT id,article_info_id,is_deleted,comment,create_time,update_time FROM drew_article_comment WHERE article_info_id = #{articleInfoId}")
    List<DrewArticleComment> selectByArticleInfoId(@Param("articleInfoId") Long articleInfoId);

}
