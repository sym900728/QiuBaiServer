package com.qiubai.service;

import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.JokeDao;
import com.qiubai.dao.impl.JokeDaoImpl;
import com.qiubai.entity.Joke;
import com.qiubai.tool.VerifyInformationTool;

@Path("/JokeService")
public class JokeService {

	private JokeDao jokeDao = new JokeDaoImpl();
	
	/**
	 * get jokes
	 * @param offset
	 * @param length
	 * @return
	 */
	@POST
	@Path("/getJokes")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Joke> getJokes(@FormParam("offset") String offset,
			@FormParam("length") String length){
		if(VerifyInformationTool.verifyGetJokesInformation(offset, length)){
			return jokeDao.getJokes(Integer.parseInt(offset), Integer.parseInt(length));
		} else {
			return null;
		}
	}
	
	/**
	 * set zan
	 * @param id
	 * @param flag
	 * @return
	 */
	@POST
	@Path("/setZan")
	@Produces({ MediaType.APPLICATION_JSON })
	public String setZan(@FormParam("id") String id,
			@FormParam("flag") String flag){
		if(VerifyInformationTool.verifySetZanInformation(id, flag)){
			if(jokeDao.setZan(Integer.parseInt(id), flag)){
				return "success";
			} else {
				return "fail";
			}
		}
		return "fail";
	}
	
	/**
	 * get joke comments
	 * @param id
	 * @return
	 */
	@POST
	@Path("/getJokeComments")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getJokeComments(@FormParam("id") String id){
		if("".equals(id)){
			return null;
		} else {
			return jokeDao.getJokeComments(id);
		}
	}
}
