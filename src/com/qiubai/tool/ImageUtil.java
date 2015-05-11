package com.qiubai.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtil {

	/**
	 * 从网站上下载图片资源
	 * 
	 * @param imgFileLocation
	 * @param urlStr
	 * @return 本地图片的存放路径
	 */
	@SuppressWarnings("resource")
	public static String downloadImage(String imgFileLocation, String urlStr) {
		File folder = new File(imgFileLocation);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		URL url;
		String path;
		String name = urlStr.substring(urlStr.lastIndexOf("/") + 1,
				urlStr.length());
		path = imgFileLocation + "/" + name;

		InputStream input = null;
		OutputStream outputStream = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();

			httpCon.setReadTimeout(60000);
			httpCon.setConnectTimeout(60000);
			input = httpCon.getInputStream();
			outputStream = new FileOutputStream(new File(path));
			byte buffer[] = new byte[1024];
			int readsize = 0;
			while ((readsize = input.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readsize);

			}
			return path;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/*public static void main(String[] args) {
		String urlStr = "http://file5.u148.net/2015/05/images/143054521832857JWRTNFJ.gif";
		String imgLocation = "D:/www/qiubai/2015/05/04";
		// String[] strs = urlStr.split("/");
		// for(int i=0;i<strs.length;i++){
		// System.out.println(strs[i]);
		// }

		System.out.println(ImageUtil.downloadImage(imgLocation, urlStr));
	}*/
}
