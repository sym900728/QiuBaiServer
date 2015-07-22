package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.qiubai.dao.CommentDao;
import com.qiubai.entity.Comment;
import com.qiubai.entity.CommentWithUser;
import com.qiubai.entity.User;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class CommentDaoImpl implements CommentDao {

	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public List<CommentWithUser> getComments(String belong, String newsid, int offset, int length) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<CommentWithUser> comments = new ArrayList<CommentWithUser>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getCommentsWithUser"));
			pstmt.setString(1, belong);
			pstmt.setString(2, newsid);
			pstmt.setInt(3, offset);
			pstmt.setInt(4, length);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				CommentWithUser cwu = new CommentWithUser();
				Comment comment = new Comment();
				User user = new User();
				comment.setId(rs.getInt("id"));
				comment.setBelong(rs.getString("belong"));
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
	public String addComment(Comment comment) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		Object param[] = {comment.getBelong(), comment.getNewsid(), comment.getUserid(), comment.getContent(), comment.getTime()};
		try {
			conn.setAutoCommit(false);
			int result1 = queryRunner.update(conn, ReadProperties.read("sql", "addComment"), param);
			int result2 = 0;
			int result3 = 0;
			if("joke".equals(comment.getBelong())){
				result2 = queryRunner.update(conn, ReadProperties.read("sql", "addJokeComments"), comment.getNewsid());
			} else if ("novel".equals(comment.getBelong())){
				result2 = queryRunner.update(conn, ReadProperties.read("sql", "addNovelComments"), comment.getNewsid());
			}
			PreparedStatement pstmt = conn.prepareStatement("select last_insert_id();");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result3 = rs.getInt(1);
			}			
			conn.commit();
			if(result1 > 0 && result2 > 0 && result3 > 0){
				return String.valueOf(result3);
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public CommentWithUser getCommentById(String id) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		CommentWithUser cwu = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getCommentById"));
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				cwu = new CommentWithUser();
				Comment comment = new Comment();
				User user = new User();
				comment.setId(rs.getInt("id"));
				comment.setBelong(rs.getString("belong"));
				comment.setNewsid(rs.getInt("newsid"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				comment.setTime(rs.getString("time"));
				user.setUserid(rs.getString("userid"));
				user.setNickname(rs.getString("nickname"));
				user.setIcon(rs.getString("icon"));
				cwu.setComment(comment);
				cwu.setUser(user);
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
		return cwu;
	}
}
