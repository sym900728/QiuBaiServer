package com.qiubai.test;

import java.util.List;

import org.junit.Test;

import com.qiubai.dao.CommentDao;
import com.qiubai.dao.impl.CommentDaoImpl;
import com.qiubai.entity.CommentWithUser;

public class TestCommentDao {

	private CommentDao commentDao;
	
	public TestCommentDao(){
		commentDao = new CommentDaoImpl();
	}
	
	@Test
	public void testGetComments(){
		String newsid = "320";
		int offset = 0;
		int length = 10;
		List<CommentWithUser> comments = commentDao.getComments(newsid, offset, length);
		for(CommentWithUser cwu : comments){
			System.out.println(cwu.getUser().toString());
			System.out.println(cwu.getComment().toString());
		}
	}
}
