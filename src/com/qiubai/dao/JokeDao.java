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
}
