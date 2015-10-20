package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.PictureDetail;

public interface PictureDetailDao {
	
	public List<PictureDetail> getPictureDetails(int pictureid);

}
