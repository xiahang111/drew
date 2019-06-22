package com.drew.controller;

import com.drew.config.ImageConfig;
import com.drew.service.ImageUploadService;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import utils.Upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/upload")
@Controller
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private ImageConfig imageConfig;


    /**
     * 有file文件时
     *
     */
    @RequestMapping("/image")
    @ResponseBody
    public String editMovieInfo(@RequestParam("imgFile") CommonsMultipartFile[] files,
                                HttpServletRequest request,
                                Map<String, Object> model,
                                HttpServletResponse response) {

        JSONObject jb=new JSONObject();
        jb.put("error", 0);
        //文件保存目录路径

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        //最大文件大小
        long maxSize = 1024 * 1024 *2;
        if(!ServletFileUpload.isMultipartContent(request)){
            jb.put("error", 1);
            jb.put("message", "请选择文件");
            return jb.toString();
        }
        String dirName  = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            jb.put("error", 1);
            jb.put("message", "目录名不正确");
            return jb.toString();
        }
        try {

            if (files!=null&&files.length>0) {
                for (CommonsMultipartFile commonsMultipartFile : files) {
                    String uploadUrl = Upload.upload(commonsMultipartFile,imageConfig.getImageUploadPath(),"333");
                    jb.put("error", 0);
                    jb.put("message", "上传成功！");
                    jb.put("url","uploadUrl");
                    return jb.toString();
                }
            }
        } catch (Exception e1) {
            jb.put("error", 1);
            jb.put("message", e1.getMessage());
            return jb.toString();
        }
        return jb.toString();

    }
}
