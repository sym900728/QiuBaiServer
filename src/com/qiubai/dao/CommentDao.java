package com.qiubai.dao;

import java.util.List;

import com.qiubai.entity.Comment;
import com.qiubai.entity.CommentWithUser;

public interface CommentDao {
	
	/**
	 * get comments (include user's information)
	 * @param belong
	 * @param newsid
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<CommentWithUser> getComments(String belong, String newsid, int offset, int length);
	
	/**
	 * add comment
	 * @param comment
	 * @return
	 */
	public String addComment(Comment comment);
	
	/**
	 * get comment by id
	 * @param id
	 * @return
	 */
	public CommentWithUser getCommentById(String id);
	
}
