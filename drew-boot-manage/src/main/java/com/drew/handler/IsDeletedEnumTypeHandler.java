package com.drew.handler;

import com.drew.item.d_enum.IsDeletedEnum;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({IsDeletedEnum.class})
public class IsDeletedEnumTypeHandler extends BaseEnumTypeHandler<IsDeletedEnum> {

}
