package com.drew.mapper;

import com.drew.item.pojo.DrewTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrewTagMapper extends Mapper<DrewTag> {

    @Select({
            "<script>",
                "select",
                "tag_name",
                "from drew_tag",
                "where id in",
                    "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                    "</foreach>",
            "</script>"
    })
    List<String> getTagNameByIds(@Param("ids")List<String> ids);

}
