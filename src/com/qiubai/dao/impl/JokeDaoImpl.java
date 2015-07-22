package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.JokeDao;
import com.qiubai.entity.Joke;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class JokeDaoImpl implements JokeDao{

	private QueryRunner queryRunner = new QueryRunner();
	
	@Override
	public List<Joke> getJokes(int offset, int length) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Joke> jokes = null;
		try {
			jokes = queryRunner.query(conn, ReadProperties.read("sql", "getJokes"), new BeanListHandler<>(Joke.class), offset, length);
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
		return jokes;
	}

	@Override
	public boolean setZan(int id, String flag) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		Object param[] = {id}; 
		try {
			int result = queryRunner.update(conn, Boolean.parseBoolean(flag) ? ReadProperties.read("sql", "zan") : ReadProperties.read("sql", "cancelZan"), param);
			if(result > 0){
				return true;
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
		return false;
	}

	@Override
	public String getJokeComments(String id) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		String result = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(ReadProperties.read("sql", "getJokeComments"));
			pstmt.setInt(1, Integer.parseInt(id));
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
