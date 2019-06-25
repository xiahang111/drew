package com.drew.config;

import com.drew.handler.BaseEnumTypeHandler;
import com.drew.item.d_enum.BaseEnum;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;
import utils.ClassUtil;

import java.util.List;

@Component
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        try {
            final List<Class<?>> allAssignedClass = ClassUtil.getAllAssignedClass(BaseEnum.class);
            allAssignedClass.forEach((clazz) -> typeHandlerRegistry.register(clazz, BaseEnumTypeHandler.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
