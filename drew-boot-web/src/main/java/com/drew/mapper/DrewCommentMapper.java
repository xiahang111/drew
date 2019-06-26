package com.drew.mapper;

import com.drew.item.pojo.DrewArticleComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrewCommentMapper extends Mapper<DrewArticleComment> {

    @Select("SELECT id,article_info_id,is_deleted,comment,create_time,update_time from drew_article_comment where article_info_id = #{articleInfoId} order by create_time desc")
    List<DrewArticleComment> getCommentByArticleId(@Param("articleInfoId")Long articleInfoId);


}
