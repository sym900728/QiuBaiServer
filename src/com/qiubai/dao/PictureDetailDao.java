package com.qiubai.dao;

import com.qiubai.entity.PictureDetail;

public interface PictureDetailDao {

	/**
	 * 增加图片的详细内容
	 * @param pictureDetail
	 * @return
	 */
	public boolean addPictureDetail(PictureDetail pictureDetail);
	
	/**
	 * 通过id查找是否已经存在PictureDetail
	 * @param id
	 * @return
	 */
	public boolean getPictureById(int id);
}
