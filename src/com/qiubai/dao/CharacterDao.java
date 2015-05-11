package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Character;

public interface CharacterDao {
	/**
	 * 增加文字版块的标题和正文以及时间 userid,char_title,char_context,char_time
	 * 
	 * @param character
	 * @return
	 */
	public boolean addCharacter(Character character);
	
	/**
	 * 通过标题来检索数据库中有没有该记录
	 * @param char_title
	 * @return
	 */
	public boolean getCharacterByTitle(String char_title);
	
	
	/**
	 * 通过limit语句来查询所有的数据库中的信息
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Character> getCharacter(int offset,int rows);
	
	/**
	 * 增加点赞,吐槽的人数
	 * @param id
	 * @param support
	 * @param tread
	 * @return
	 */
	public boolean addCharacterSupport(int id,String support,String tread);

}
