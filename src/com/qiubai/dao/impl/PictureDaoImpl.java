package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.PictureDao;
import com.qiubai.entity.Picture;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class PictureDaoImpl implements PictureDao{
	
	QueryRunner runner = new QueryRunner();

	@Override
	public List<Picture> getPictures(int offset, int length) {
		
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Picture> pictures = null;
		try {
			pictures = runner.query(conn, ReadProperties.read("sql", "getPictures"), new BeanListHandler<>(Picture.class), offset, length);
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
		return pictures;
	}

	@Override
	public String getPictureComments(int id) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		String result = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getPictureComments"));
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
