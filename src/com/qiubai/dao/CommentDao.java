package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Comment;
import com.qiubai.entity.CommentWithUser;

public interface CommentDao {
	
	
	/**
	 * get comments (include user's information)
	 * @param newsid
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<CommentWithUser> getComments(String newsid, int offset, int length);
	
	/**
	 * add comment
	 * @param comment
	 * @return true: success; false: fail
	 */
	public boolean addComment(Comment comment);
}
