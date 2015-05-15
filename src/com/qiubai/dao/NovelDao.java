package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Novel;

public interface NovelDao {
	
	public List<Novel> getNovels(int offset, int length);
}
