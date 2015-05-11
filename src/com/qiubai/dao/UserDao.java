package com.qiubai.dao;

import com.qiubai.entity.User;

public interface UserDao {

	/**
	 * user login
	 * @param userid
	 * @param password
	 * @return
	 */
	public User login(String userid, String password);
	
	/**
	 * user register
	 * @param user
	 * @return
	 */
	public boolean register(User user);

	/**
	 * get user via userid(just include userid, nickname, icon)
	 * @param userid
	 * @return
	 */
	public User getUser(String userid);

	/**
	 * get user include token(include userid, token, nickname, icon)
	 * @param userid
	 * @return
	 */
	public User getUserIncludeToken(String userid);
	
	/**
	 * get user include password
	 * @param userid
	 * @return
	 */
	public User getUserIncludePassword(String userid);
	
	/**
	 * change nickname
	 * @param userid
	 * @param nickname
	 * @return true: success; false: fail
	 */
	public boolean changeNickname(String userid, String nickname);
	
	/**
	 * change password
	 * @param userid
	 * @param password
	 * @return true: success; false: fail
	 */
	public boolean changePassword(String userid, String password);
	
	/**
	 * and user icon
	 * @param userid
	 * @param path
	 * @return
	 */
	public boolean addUserIcon(String userid, String path);
}
