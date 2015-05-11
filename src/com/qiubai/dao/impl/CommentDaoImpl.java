package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.qiubai.dao.CommentDao;
import com.qiubai.entity.Comment;
import com.qiubai.entity.CommentWithUser;
import com.qiubai.entity.User;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class CommentDaoImpl implements CommentDao {

	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public List<CommentWithUser> getComments(String newsid, int offset, int length) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<CommentWithUser> comments = new ArrayList<CommentWithUser>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getCommentsWithUser"));
			pstmt.setString(1, newsid);
			pstmt.setInt(2, offset);
			pstmt.setInt(3, length);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				CommentWithUser cwu = new CommentWithUser();
				Comment comment = new Comment();
				User user = new User();
				comment.setId(rs.getInt("id"));
				comment.setNewsid(rs.getInt("newsid"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				comment.setTime(rs.getString("time"));
				user.setUserid(rs.getString("userid"));
				user.setNickname(rs.getString("nickname"));
				user.setIcon(rs.getString("icon"));
				cwu.setComment(comment);
				cwu.setUser(user);
				comments.add(cwu);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comments;
	}

	@Override
	public boolean addComment(Comment comment) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		Object param[] = {comment.getNewsid(), comment.getUserid(), comment.getContent(), comment.getTime()};
		try {
			int result = queryRunner.update(conn, ReadProperties.read("sql", "addComment"), param);
			if(result > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
