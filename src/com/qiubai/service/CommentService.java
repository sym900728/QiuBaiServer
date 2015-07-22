package com.qiubai.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.CommentDao;
import com.qiubai.dao.UserDao;
import com.qiubai.dao.impl.CommentDaoImpl;
import com.qiubai.dao.impl.UserDaoImpl;
import com.qiubai.entity.Comment;
import com.qiubai.entity.CommentWithUser;
import com.qiubai.entity.User;
import com.qiubai.tool.VerifyInformationTool;

@Path("CommentService")
public class CommentService {
	
	private UserDao userDao = new UserDaoImpl();
	private CommentDao commentDao = new CommentDaoImpl();
	
	/**
	 * add comment
	 * @param token
	 * @param belong
	 * @param newsid
	 * @param userid
	 * @param content
	 * @return
	 */
	@POST
	@Path("/addComment/{token}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String addComment(@PathParam("token") String token,
			@FormParam("belong") String belong,
			@FormParam("newsid") String newsid, 
			@FormParam("userid") String userid,
			@FormParam("content") String content){
		if(VerifyInformationTool.verifyCommentInformation(belong, token, newsid, userid, content)){
			User user = userDao.getUserIncludeToken(userid);
			if( user == null){
				return "fail";
			} else if( !token.equals(user.getToken()) ){
				return "fail";
			} else {
				Comment comment = new Comment();
				comment.setNewsid(Integer.parseInt(newsid));
				comment.setBelong(belong);
				comment.setUserid(userid);
				comment.setContent(content);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = sdf.format(System.currentTimeMillis());
				comment.setTime(time);
				String result = commentDao.addComment(comment);
				if(result != null){
					return result;
				} else {
					return "fail";
				}
			}
		} else {
			return "fail";
		}
	}
	
	/**
	 * get comments
	 * @param belong
	 * @param newsid
	 * @param offset
	 * @param length
	 * @return a list of comments
	 */
	@POST
	@Path("/getComments")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CommentWithUser> getComments(@FormParam("belong") String belong,
			@FormParam("newsid") String newsid,
			@FormParam("offset") String offset, 
			@FormParam("length") String length){
		if("".equals(belong) || "".equals(newsid) || "".equals(offset) || "".equals(length)){
			return null;
		} else {
			return commentDao.getComments(belong, newsid, Integer.parseInt(offset), Integer.parseInt(length));
		}
	}
	
	@POST
	@Path("/getCommentById/{token}")
	@Produces({ MediaType.APPLICATION_JSON })
	public CommentWithUser getCommentById(@PathParam("token") String token,
			@FormParam("id") String id,
			@FormParam("userid") String userid){
		if(VerifyInformationTool.verifyGetCommentByIdInformation(token, id, userid)){
			User user = userDao.getUserIncludeToken(userid);
			if( user == null){
				return null;
			} else if( !token.equals(user.getToken()) ){
				return null;
			} else {
				return commentDao.getCommentById(id);
			}
		} else {
			return null;
		}
	}
}
