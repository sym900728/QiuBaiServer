package com.qiubai.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentWithUser {
	private User user;
	private Comment comment;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
