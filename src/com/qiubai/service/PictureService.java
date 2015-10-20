package com.qiubai.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.PictureDao;
import com.qiubai.dao.impl.PictureDaoImpl;
import com.qiubai.entity.Picture;
import com.qiubai.tool.VerifyInformationTool;

@Path("/PictureService")
public class PictureService {

	private PictureDao pictureDao = new PictureDaoImpl();

	@POST
	@Path("/getPictures")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Picture> getPictures(@FormParam("offset") String offset,
			@FormParam("length") String length) {

		if (VerifyInformationTool.verifyGetPicturesInformation(offset, length)) {
			return pictureDao.getPictures(Integer.parseInt(offset), Integer.parseInt(length));
		}

		return null;
	}

	
	@POST
	@Path("/getPictureComments")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getPictureComments(@FormParam("id") String id){
		if(!"".equals(id.trim())){
			return pictureDao.getPictureComments(Integer.parseInt(id));
		} 
		return null;
	}
}
