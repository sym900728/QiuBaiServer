package com.qiubai.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.qiubai.dao.UserDao;
import com.qiubai.dao.impl.UserDaoImpl;
import com.qiubai.entity.User;
import com.qiubai.tool.VerifyInformationTool;
import com.qiubai.util.JavaMail;
import com.qiubai.util.SystemUtil;

@Path("/UserService")
public class UserService {

	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * user register
	 * @param email
	 * @param nickname
	 * @param password
	 * @return
	 */
	@POST
	@Path("/register")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String register(@FormParam("email") String email,
			@FormParam("nickname") String nickname,
			@FormParam("password") String password) {
		if(VerifyInformationTool.verifyRegisterInformation(email, nickname, password)){
			if(userDao.getUser(email) != null){
				return "exist";
			} else {
				User user = new User();
				user.setUserid(email);
				user.setNickname(nickname.trim());
				user.setPassword(password);
				user.setIcon("default");
				user.setToken(UUID.randomUUID().toString());
				if(userDao.register(user)){
					return "success";
				} else {
					return "fail";
				}
			}
		} else {
			return "fail";
		}
	}
	
	/**
	 * login
	 * @param userid
	 * @param password
	 * @return
	 */
	@POST
	@Path("/login")
	@Produces({ MediaType.APPLICATION_JSON })
	public User login(@FormParam("userid") String userid,
			@FormParam("password") String password) {
		if(VerifyInformationTool.verifyLoginInformation(userid, password)){
			return userDao.login(userid, password);
		} else {
			return null;
		}
	}
	
	/**
	 * forget password
	 * @param userid
	 * @return
	 */
	@POST
	@Path("/forgetPassword")
	@Produces({ MediaType.TEXT_PLAIN })
	public String forgetPassword(@FormParam("userid") String userid){
		if(VerifyInformationTool.verifyForgetPasswordInformation(userid)){
			User user = userDao.getUserIncludePassword(userid);
			if(user != null){
				JavaMail javaMail = new JavaMail();
				if(javaMail.sendEmail("糗事百科", "糗事百科" + "<br/>尊敬的：" + user.getNickname() + 
						"<br/>您的密码为：" + user.getPassword() + "<br/>请立即登录糗百手机客户端修改密码。", user.getUserid())){
					return "success";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	
	/**
	 * change nickname
	 * @param token
	 * @param userid
	 * @param nickname
	 * @return
	 */
	@POST
	@Path("/changeNickname/{token}")
	@Produces({ MediaType.TEXT_PLAIN})
	public String changeNickname(@PathParam("token") String token,
			@FormParam("userid") String userid,
			@FormParam("nickname") String nickname){
		if(VerifyInformationTool.verifyChangeNicknameInformation(userid, token, nickname)){
			User user = userDao.getUserIncludeToken(userid);
			if(user != null){
				if( token.equals(user.getToken()) ){
					if(userDao.changeNickname(userid, nickname.trim())){
						return "success";
					}
				}
			}
		}
		return "fail";
	}
	
	/**
	 * change password
	 * @param token
	 * @param userid
	 * @param password
	 * @return
	 */
	@POST
	@Path("/changePassword/{token}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String changePassword(@PathParam("token") String token,
			@FormParam("userid") String userid,
			@FormParam("originPassword") String originPassword,
			@FormParam("newPassword") String newPassword){
		if(VerifyInformationTool.verifyChangePasswordInformation(userid, token, originPassword, newPassword)){
			User user = userDao.getUserIncludePassword(userid);
			if(user != null){
				if(token.equals(user.getToken()) || originPassword.equals(user.getPassword())){
					if(userDao.changePassword(userid, newPassword)){
						return "success";
					}
				}
			}
		}
		return "fail";
	}
	
	/**
	 * upload user header
	 * @param token
	 * @param userid
	 * @param request
	 * @param response
	 * @return
	 */
	@POST
	@Path("/uploadIcon/{token}/{userid}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces(value = MediaType.TEXT_PLAIN)
	public String uploadIcon(
			@PathParam("token") String token,
			@PathParam("userid") String userid, 
			@Context HttpServletRequest request, 
			@Context HttpServletResponse response) {
		if(VerifyInformationTool.verifyUploadIconInformation(token, userid)){
			User user = userDao.getUserIncludeToken(userid);
			if(user != null){
				if(token.equals(user.getToken())){
					String path = SystemUtil.changePath(SystemUtil.uploadIcon(userid, request), request);
					if(path != null){
						if( userDao.addUserIcon(userid, path) ){
							return "success";
						}
					}
				}
			}
		}
		return "fail";
	}
}
