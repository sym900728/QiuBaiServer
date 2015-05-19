package com.qiubai.service;

import java.util.List;

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
}
