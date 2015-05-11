package com.qiubai.server;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qiubai.entity.PictureDetail;
import com.qiubai.tool.DateUtils;
import com.qiubai.tool.ImageUtil;
import com.qiubai.tool.ReadProperties;

public class PictureDetailManager {

	
	public List<PictureDetail> getPictureDetailByUrl(String url,int id){
		
		List<PictureDetail> pictureDetails = new ArrayList<PictureDetail>();
		PictureDetail pictureDetail;
		
		String imgFileLocation = "D:/www/qiubai/"+DateUtils.getCurrentDayTime();
		
		String ip = ReadProperties.read("website", "web_ip");
		
		String parseHtml = ParseHtml.getHtmlContent(url, "utf-8");
		
		Document doc = Jsoup.parse(parseHtml);
		
		Elements contents = doc.getElementsByClass("content");
		
//		System.out.println(contents.select("p").text());
		Elements img_addresss = contents.select("img");
		for(Element element:img_addresss){
			pictureDetail = new PictureDetail();
			
			//http://file5.u148.net/2015/04/images/1430388077540FDAIT8Y4A.jpg
			String img_address = element.attr("src").toString();
			String pic_address = ImageUtil.downloadImage(imgFileLocation, img_address);
			
			pictureDetail.setId(id);
			pictureDetail.setPic_address(pic_address.replace("D:/www", ip));
			
			pictureDetails.add(pictureDetail);
		}
		return pictureDetails;
	}
	
	/*public static void main(String[] args) {
		PictureDetailManager pictureDetailManager = new PictureDetailManager();
		String url = "http://www.u148.net/article/124778.html";
		pictureDetailManager.getPictureDetailByUrl(url,1);
	}*/
}
