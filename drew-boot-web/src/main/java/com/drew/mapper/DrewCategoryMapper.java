package com.drew.mapper;

import com.drew.item.pojo.DrewCategory;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrewCategoryMapper extends Mapper<DrewCategory> {


    @Select("SELECT category_name from drew_category where id = #{id}")
    String getNameById(Long id);

    @Select("SELECT category_name from drew_category where category_parent_id = #{parentId}")
    List<String> getNamesByParentId(Long parentId);

    @Select("SELECT id from drew_category where category_parent_id = #{parentId}")
    List<String> getIdsByParentId(Long parentId);

}
