package com.drew.controller;


import com.drew.entity.ResponseResult;
import com.drew.item.dto.DrewEverydaySentenceDTO;
import com.drew.item.pojo.DrewEverdaySentence;
import com.drew.service.DrewSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sentence")
public class DrewSentenceController {


    @Autowired
    private DrewSentenceService drewSentenceService;


    @RequestMapping("getAll")
    public ResponseResult getAllSentence() {
        List<DrewEverdaySentence> everdaySentences = new ArrayList<>();

        everdaySentences = drewSentenceService.getAllsentence();

        if (null == everdaySentences || everdaySentences.size() <= 0) {
            return ResponseResult.error;
        }

        List<DrewEverydaySentenceDTO> sentenceDTOS = new ArrayList<>();

        for (DrewEverdaySentence sentence : everdaySentences) {
            sentenceDTOS.add(new DrewEverydaySentenceDTO(sentence));

        }

        return new ResponseResult(sentenceDTOS);
    }

    @RequestMapping("getToday")
    public ResponseResult getTodaySentence(){

        DrewEverydaySentenceDTO sentenceDTO = drewSentenceService.getTodaySentence();

        if(null == sentenceDTO){

            return ResponseResult.error;
        }

        return new ResponseResult(sentenceDTO);

    }
}
