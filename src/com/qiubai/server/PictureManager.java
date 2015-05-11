package com.qiubai.server;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qiubai.entity.Picture;
import com.qiubai.tool.DateUtils;
import com.qiubai.tool.ReadProperties;

public class PictureManager {
	public List<Picture> getPictureByUrl(String url){
		List<Picture> pictures = new ArrayList<Picture>();
		Picture picture;
		String webUrl = ReadProperties.read("website", "picture_url");
		String parseHtml = ParseHtml.getHtmlContent(url, "utf-8");
		
		Document doc = Jsoup.parse(parseHtml);
		
		Elements contents = doc.getElementsByAttributeValue("class", "mainlist");
		for(Element content:contents){
			Elements element_img = content.getElementsByAttributeValue("class", "mainlist_img");
			Elements element_img_address = element_img.get(0).getElementsByTag("img");
			String img_address = element_img_address.get(0).attr("src").toString();
			
			String href_pic = element_img.get(0).getElementsByAttribute("href").toString();
			String href_pic2 = href_pic.substring(href_pic.indexOf("\"")+1);
			String href_pic3 = href_pic2.substring(0, href_pic2.indexOf("\""));
			
			if(img_address.substring(img_address.length()-3).equals("jpg")){
				picture = new Picture();
				String img_title_detail = element_img_address.get(0).attr("alt").toString();//点击进入[令人崩溃的设计，设计师咱俩需要聊一聊……]
				
				String img_title = img_title_detail.substring(img_title_detail.indexOf("[")+1,img_title_detail.length()-1);//令人崩溃的设计，设计师咱俩需要聊一聊……
				String pic_time = DateUtils.getCurrentTime();
				String pic_extra = webUrl+href_pic3;
				
				
				picture.setUser_id("qiubaiadmin@163.com");
				picture.setPic_title(img_title);
				picture.setPic_time(pic_time);
				picture.setPic_extra(pic_extra);
				
				pictures.add(picture);
				
			}
		}
		
		return pictures;
		
	}
	
}
