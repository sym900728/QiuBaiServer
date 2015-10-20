package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Picture;

public interface PictureDao {
	
	/**
	 * get pictures
	 * @param offset
	 * @param lenght
	 * @return
	 */
	public List<Picture> getPictures(int offset, int lenght);
	
	/**
	 * get picture comments
	 * @param id
	 * @return
	 */
	public String getPictureComments(int id);
}
