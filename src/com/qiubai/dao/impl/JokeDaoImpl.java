package com.qiubai.dao.impl;

import java.sql.Connection;
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

}
