package com.qiubai.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.CharacterDao;
import com.qiubai.dao.impl.CharacterDaoImpl;
import com.qiubai.entity.Character;
import com.qiubai.server.CharacterManager;
import com.qiubai.tool.ReadProperties;

@Path("/CharacterService")
public class CharacterService {

	private CharacterDao characterDao = new CharacterDaoImpl();

	/**
	 * 通过limit语句从数据库中获取所有的数据
	 * 
	 * @param offset
	 * @param rows
	 * @return
	 */
	@POST
	@Path("/getCharacters")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Character> getCharacters(@FormParam("offset") String offset,
			@FormParam("rows") String rows) {

		List<Character> chars = characterDao.getCharacter(
				Integer.parseInt(offset), Integer.parseInt(rows));
		return chars;

	}

	//暂时把增加消息方法给注释
	/**
	 * 定时的从网站上读数据，往数据库中添加
	 * 
	 * @return
	 */
	/*@GET
	@Path("/addCharacter")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCharacter() {

		CharacterManager cm = new CharacterManager();
		List<Character> characters = cm.getByUrl(ReadProperties.read("website",
				"pengfu_character"));
		for (Character character : characters) {
			if (characterDao.getCharacterByTitle(character.getChar_title())) {
				return "Exist";
			} else {
				characterDao.addCharacter(character);
			}
		}
		return "success";
	}*/

	@POST
	@Path("/addCharacterSupportOppose")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCharacterSupportOppose(@FormParam("id") String id,
			@FormParam("support") String support,
			@FormParam("oppose") String oppose) {

		boolean flag = characterDao.addCharacterSupport(Integer.parseInt(id),
				support, oppose);
		if (flag == true) {
			return "true";
		}
		return "false";

	}
}
