package com.qiubai.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyInformationTool {
	
	public static boolean verifyRegisterInformation(String email, String nickname, String password){
		String regex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if("".equals(email.trim()) || !matcher.matches()){
			return false;
		} else if("".equals(nickname.trim()) || nickname.trim().length() > 10 || nickname.trim().length() < 3) {
			return false;
		} else if("".equals(password) || password.length() > 20 || password.length() < 6){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verifyLoginInformation(String email, String password){
		String regex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if("".equals(email.trim()) || !matcher.matches()){
			return false;
		} else if("".equals(password)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verifyForgetPasswordInformation(String email){
		String regex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if("".equals(email.trim()) || !matcher.matches()){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verifyCommentInformation(String token, String newsid, String userid, String content){
		if("".equals(token.trim()) || "".equals(newsid.trim()) || "".equals(userid.trim()) || "".equals(content.trim())){
			return false;
		} else if (content.trim().length() > 500){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verifyChangePasswordInformation(String userid, String token, String originPassword, String newPassword){
		if("".equals(token.trim()) || "".equals(userid) || "".equals(originPassword) || "".equals(newPassword)){
			return false;
		} else if(originPassword.length() < 6 || originPassword.length() > 20){
			return false;
		} else if(newPassword.length() < 6 || newPassword.length() > 20){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verifyChangeNicknameInformation(String userid, String token, String nickname){
		if("".equals(token.trim()) || "".equals(userid) || "".equals(nickname)){
			return false;
		} else if(nickname.trim().length() < 3 || nickname.trim().length() > 10){
			return false;
		}
		return true;
	}
	
	public static boolean verifyUploadIconInformation(String token, String userid){
		if("".equals(token.trim()) || "".equals(userid)){
			return false;
		}
		return true;
	}
	
}
