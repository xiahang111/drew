package com.drew.service;

import com.drew.item.pojo.DrewArticleInfo;
import com.drew.mapper.DrewArticleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrewArticleService {

    @Autowired
    private DrewArticleInfoMapper drewArticleInfoMapper;

    public List<DrewArticleInfo> getAllDrewArticleInfo(){
        return drewArticleInfoMapper.selectAll();
    }
}
