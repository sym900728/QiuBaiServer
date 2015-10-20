package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.VideoDao;
import com.qiubai.entity.Video;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class VideoDaoImpl implements VideoDao{

	QueryRunner runner = new QueryRunner();
	
	@Override
	public List<Video> getVideos(int offset, int length) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Video> videos = null;
		try {
			videos = runner.query(conn, ReadProperties.read("sql", "getVideos"), new BeanListHandler<>(Video.class), offset, length);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null || !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return videos;
	}

	@Override
	public String getVideoComments(int id) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		String result = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getVideoComments"));
			pstmt.setInt(1, Integer.valueOf(id));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null || !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
