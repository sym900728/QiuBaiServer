package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Video;

public interface VideoDao {

	public List<Video> getVideos(int offset, int length);
	
	public String getVideoComments(int id);
}
