package com.qiubai.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.NovelDao;
import com.qiubai.dao.impl.NovelDaoImpl;
import com.qiubai.entity.Novel;
import com.qiubai.tool.VerifyInformationTool;

@Path("NovelService")
public class NovelService {
	
	private NovelDao novelDao = new NovelDaoImpl();
	
	/**
	 * @param offset
	 * @param length
	 * @return
	 */
	@POST
	@Path("/getNovels")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Novel> getNovels(@FormParam("offset") String offset,
			@FormParam("length") String length) {
		if(VerifyInformationTool.verifyGetNovelsInformation(offset, length)){
			return novelDao.getNovels(Integer.parseInt(offset), Integer.parseInt(length));
		} else {
			return null;
		}
	}
}
