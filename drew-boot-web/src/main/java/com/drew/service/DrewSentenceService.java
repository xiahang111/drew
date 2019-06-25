package com.drew.service;

import com.drew.item.dto.DrewEverydaySentenceDTO;
import com.drew.item.pojo.DrewEverdaySentence;
import com.drew.mapper.DrewSentenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.DateUtils;

import java.util.Date;
import java.util.List;

@Service
public class DrewSentenceService {

    @Autowired
    public DrewSentenceMapper drewSentenceMapper;

    public List<DrewEverdaySentence> getAllsentence(){
        return drewSentenceMapper.selectAll();
    }

    public DrewEverydaySentenceDTO getTodaySentence(){

        String today = DateUtils.dateToString(new Date(),DateUtils.DATE_FORMAT);


        List<DrewEverdaySentence> everdaySentences = drewSentenceMapper.selectAll();

        DrewEverdaySentence everdaySentence = everdaySentences.stream()
                .filter(e -> DateUtils.dateToString(e.getShowTime(),DateUtils.DATE_FORMAT).equals(today))
                .findAny()
                .orElse(null);

        if(null != everdaySentence){

            DrewEverydaySentenceDTO result = new DrewEverydaySentenceDTO(everdaySentence);

            result.setShowTime(DateUtils.dateToString(everdaySentence.getShowTime(),DateUtils.DATE_FORMAT_CHINESE));

            return result;
        }

        return null;
    }
}
