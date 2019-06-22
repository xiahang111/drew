package com.drew.controller;

import com.alibaba.fastjson.JSONObject;
import com.drew.config.BlogWebConfig;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title 		: MgrAticleUploadController</p>
 * <p>Description   : kindEditor圖片上床工具類</p>
 * <p>DevelopTools  : Eclipse_x64</p>
 * <p>DevelopSystem : windows10_x64</p>
 * <p>Company 		: com.liangzi</p>
 * @author  		: Liangzi
 * @date 			: 2016年12月27日 下午7:57:42
 * @version 		: 1.0
 */
@Controller("mgrAticleUploadController")
@RequestMapping("/mgr")
public class MgrAticleUploadController {
	
	/**
	 * 上传文章图片
	 * @param request
	 * @param response
	 * @param imgFile
	 */
	@RequestMapping("/fileUpload.html")
	@ResponseBody
	public void fileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam("imgFile") MultipartFile[] imgFile) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			// 文件保存本地目录路径
			String savePath = request.getSession().getServletContext().getRealPath("") + BlogWebConfig.PICTURE_SPACE_URL;
//			String savePath = BlogWebConfig.PICTURE_SPACE_URL;
//			String savePath = BlogWebConfig.PICTURE_SPACE_URL;
			// 文件保存目录URL
			String saveUrl = BlogWebConfig.PICTURE_URL;
			if (!ServletFileUpload.isMultipartContent(request)) {
				out.print(getError("请选择文件。"));
				out.close();
				return;
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				uploadDir.mkdir();
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				out.print(getError("上传目录没有写权限。"));
				out.close();
				return;
			}
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			// 定义允许上传的文件扩展名
			Map<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");
			if (!extMap.containsKey(dirName)) {
				out.print(getError("目录名不正确。"));
				out.close();
				return;
			}
			// 创建文件夹
//			savePath += dirName + BlogWebConfig.PATH_LINE;
//			saveUrl += dirName + BlogWebConfig.PATH_LINE;
//			File saveDirFile = new File(savePath);
//			if (!saveDirFile.exists()) {
//				saveDirFile.mkdirs();
//			}
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			String ymd = sdf.format(new Date());
//			savePath += ymd + BlogWebConfig.PATH_LINE;
//			saveUrl += ymd + BlogWebConfig.PATH_LINE;
//			File dirFile = new File(savePath);
//			if (!dirFile.exists()) {
//				dirFile.mkdirs();
//			}
			// 最大文件大小
			long maxSize = 1000000;
			// 保存文件
			for (MultipartFile iFile : imgFile) {
				String fileName = iFile.getOriginalFilename();
				// 检查文件大小
				if (iFile.getSize() > maxSize) {
					out.print(getError("上传文件大小超过限制。"));
					out.close();
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					// return getError("上传文件扩展名是不允许的扩展名。\n只允许" +
					// extMap.get(dirName) + "格式。");
					out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					out.close();
					return;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					System.out.println(uploadedFile);
					// 写入文件
					FileUtils.copyInputStreamToFile(iFile.getInputStream(), uploadedFile);
				} catch (Exception e) {
					out.print(getError("上传文件失败。"));
					out.close();
					return;
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				out.print(obj.toJSONString());
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
  
    private Map<String, Object> getError(String errorMsg) {  
        Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("error", 1);  
        errorMap.put("message", errorMsg);  
        return errorMap;  
    }  
      
    /** 
      * 文件空间 
      * @param request {@link HttpServletRequest} 
      * @param response {@link HttpServletResponse} 
      * @return json 
      */  
	@RequestMapping("/upload.html")
	@ResponseBody
	public void fileManager(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 根目录路径，可以指定绝对路径
			String rootPath = request.getSession().getServletContext().getRealPath("")
					+ BlogWebConfig.PICTURE_SPACE_URL;
			// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
			String rootUrl = BlogWebConfig.PICTURE_URL;
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			// 图片扩展名
			String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
			String dirName = request.getParameter("dir");
			if (dirName != null) {
				if (!Arrays.<String>asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
					out.print("无效的文件夹。");
					out.close();
					return;
				}
				rootPath += dirName + BlogWebConfig.PATH_LINE;
				rootUrl += dirName + BlogWebConfig.PATH_LINE;
				File saveDirFile = new File(rootPath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
			}
			// 根据path参数，设置各路径和URL
			String path = request.getParameter("path") != null ? request.getParameter("path") : "";
			String currentPath = rootPath + path;
			String currentUrl = rootUrl + path;
			String currentDirPath = path;
			String moveupDirPath = "";
			if (!"".equals(path)) {
				String str = currentDirPath.substring(0, currentDirPath.length() - 1);
				moveupDirPath = str.lastIndexOf(BlogWebConfig.PATH_LINE) >= 0
						? str.substring(0, str.lastIndexOf(BlogWebConfig.PATH_LINE) + 1) : "";
			}
			// 排序形式，name or size or type
			String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
			// 不允许使用..移动到上一级目录
			if (path.indexOf("..") >= 0) {
				out.print("访问权限拒绝。");
				out.close();
				return;
			}
			// 最后一个字符不是/
			if (!"".equals(path) && !path.endsWith(BlogWebConfig.PATH_LINE)) {
				out.print("无效的访问参数验证。");
				out.close();
				return;
			}
			// 目录不存在或不是目录
			File currentPathFile = new File(currentPath);
			if (!currentPathFile.isDirectory()) {
				out.print("文件夹不存在。");
				out.close();
				return;
			}
			// 遍历目录取的文件信息
			List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
			if (currentPathFile.listFiles() != null) {
				for (File file : currentPathFile.listFiles()) {
					Hashtable<String, Object> hash = new Hashtable<String, Object>();
					String fileName = file.getName();
					if (file.isDirectory()) {
						hash.put("is_dir", true);
						hash.put("has_file", (file.listFiles() != null));
						hash.put("filesize", 0L);
						hash.put("is_photo", false);
						hash.put("filetype", "");
					} else if (file.isFile()) {
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						hash.put("is_dir", false);
						hash.put("has_file", false);
						hash.put("filesize", file.length());
						hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
						hash.put("filetype", fileExt);
					}
					hash.put("filename", fileName);
					hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
					fileList.add(hash);
				}
			}
			if ("size".equals(order)) {
				Collections.sort(fileList, new SizeComparator());
			} else if ("type".equals(order)) {
				Collections.sort(fileList, new TypeComparator());
			} else {
				Collections.sort(fileList, new NameComparator());
			}
			JSONObject result = new JSONObject();
			result.put("moveup_dir_path", moveupDirPath);
			result.put("current_dir_path", currentDirPath);
			result.put("current_url", currentUrl);
			result.put("total_count", fileList.size());
			result.put("file_list", fileList);
			out.println(result.toJSONString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
      
	private class NameComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	private class SizeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private class TypeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}
	 
}
