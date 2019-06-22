package com.drew.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import utils.Upload;

import java.io.File;

@Service
public class ImageUploadService {

    @Transactional
    public int editMovieInfo(int id, MultipartFile file, String uploadDir) {
        try {
            String imgUrl = null;
            String filename = Upload.upload(file, uploadDir, file.getOriginalFilename());
            if (!StringUtils.isEmpty(filename)) {
                imgUrl = new File(uploadDir).getName() + "/" + filename;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
