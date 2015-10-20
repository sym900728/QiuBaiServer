package com.qiubai.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.VideoDao;
import com.qiubai.dao.impl.VideoDaoImpl;
import com.qiubai.entity.Video;
import com.qiubai.tool.VerifyInformationTool;

@Path("/VideoService")
public class VideoService {
	
	private VideoDao videoDao = new VideoDaoImpl();

	@POST
	@Path("/getVideos")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Video> getVideos(@FormParam("offset") String offset,
			@FormParam("length") String length) {

		if (VerifyInformationTool.verifyGetVideosInformation(offset, length)) {
			return videoDao.getVideos(Integer.parseInt(offset), Integer.parseInt(length));
		}

		return null;
	}

	
	@POST
	@Path("/getVideoComments")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getVideoComments(@FormParam("id") String id){
		if(!"".equals(id.trim())){
			return videoDao.getVideoComments(Integer.parseInt(id));
		} 
		return null;
	}
}
