package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Joke;

public interface JokeDao {
	
	/**
	 * get jokes
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<Joke> getJokes(int offset, int length);
	
	/**
	 * set zan
	 * @param id
	 * @param flag
	 * @return
	 */
	public boolean setZan(int id, String flag);
	
	/**
	 * get joke comments
	 * @param id
	 * @return
	 */
	public String getJokeComments(String id);
}
