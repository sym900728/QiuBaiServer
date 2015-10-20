package com.qiubai.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.PictureDetailDao;
import com.qiubai.dao.impl.PictureDetailDaoImpl;
import com.qiubai.entity.PictureDetail;

@Path("/PictureDetailService")
public class PictureDetailService {
	private PictureDetailDao pictureDetailDao = new PictureDetailDaoImpl();

	@POST
	@Path("/getPictureDetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PictureDetail> getPictureDetails(@FormParam("pictureid") String pictureid){
		if("".equals(pictureid.trim())){
			return null;
		} else {
			return pictureDetailDao.getPictureDetails(Integer.parseInt(pictureid));
		}
	}

}
