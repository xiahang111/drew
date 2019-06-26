package com.drew.service;

import com.drew.item.d_enum.IsDeletedEnum;
import com.drew.item.dto.ArticleCommentDTO;
import com.drew.item.pojo.DrewArticleComment;
import com.drew.mapper.DrewCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DrewCommentService {

    @Autowired
    private DrewCommentMapper drewCommentMapper;

    public void addDrewComment(String articleId,String comment) throws Exception{

        DrewArticleComment drewArticleComment = new DrewArticleComment();
        drewArticleComment.setArticleInfoId(Long.parseLong(articleId));

        drewArticleComment.setComment(comment);
        drewArticleComment.setIsDeletedEnum(IsDeletedEnum.NOT_DELETED);

        Date now = new Date();
        drewArticleComment.setCreateTime(now);
        drewArticleComment.setUpdateTime(now);

        drewCommentMapper.insert(drewArticleComment);

        Thread.sleep(500l);
    }

    public List<ArticleCommentDTO> getCommentByArticleId(String articleId){

        List<ArticleCommentDTO> result = new ArrayList<>();

        List<DrewArticleComment> comments = drewCommentMapper.getCommentByArticleId(Long.parseLong(articleId));

        if(comments.size()>=0){
            int floor = 1;
            for (DrewArticleComment comment:comments) {

                ArticleCommentDTO articleCommentDTO = new ArticleCommentDTO();
                articleCommentDTO.setFloor(floor);floor++;
                articleCommentDTO.setComment(comment.getComment());
                articleCommentDTO.setCommentDate(DateUtils.dateToString(comment.getCreateTime(),DateUtils.DATE_FORMAT));
                result.add(articleCommentDTO);
            }
        }

        return result;
    }
}
