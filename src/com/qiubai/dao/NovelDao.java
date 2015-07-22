package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Novel;

public interface NovelDao {
	
	/**
	 * get novels
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<Novel> getNovels(int offset, int length);
	
	/**
	 * get novel comments
	 * @param id
	 * @return
	 */
	public String getNovelComments(String id);
}
