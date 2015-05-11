package com.qiubai.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class SystemUtil {
	
	public static String uploadIcon(String userId, HttpServletRequest request){
		String path = null;
		try {
			request.setCharacterEncoding("utf-8");
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				String savePath = request.getSession().getServletContext().getRealPath("/") + "upload/" + userId + "/";
				String tempPath = request.getSession().getServletContext().getRealPath("/") + "upload/temp/";
				File saveFile = new File(savePath);
				File tempFile = new File(tempPath);
				System.out.println(savePath);
				System.out.println(tempPath);
				if (!saveFile.isDirectory()){
					saveFile.mkdirs();
				}
				if (!tempFile.isDirectory()){
					tempFile.mkdirs();
				}
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024 * 4);
				factory.setRepository(tempFile);
				ServletFileUpload uploader = new ServletFileUpload(factory);
				uploader.setSizeMax(4 * 1024 * 1024);

				List<FileItem> fileItems = uploader.parseRequest(request);
				for (FileItem item : fileItems) {
					if (!item.isFormField()) {
						String fileName = item.getName();
						File file = new File(savePath + fileName);
						item.write(file);
						path = savePath + fileName;
					} 
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String changePath(String path, HttpServletRequest request){
		String ip = request.getLocalAddr().toString();
		String port = String.valueOf(request.getLocalPort());
		return "http://" + ip + ":" + port + "/" + path.substring(path.lastIndexOf("QiuBaiServer"));
	}
}
