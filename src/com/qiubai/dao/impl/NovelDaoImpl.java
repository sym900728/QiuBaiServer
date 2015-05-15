package com.qiubai.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.Connection;
import com.qiubai.dao.NovelDao;
import com.qiubai.entity.Novel;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class NovelDaoImpl implements NovelDao{

	private QueryRunner queryRunner = new QueryRunner();
	
	@Override
	public List<Novel> getNovels(int offset, int length) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Novel> novels = null;
		try {
			novels = queryRunner.query(conn, ReadProperties.read("sql", "getNovels"), new BeanListHandler<>(Novel.class), offset, length);
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
		return novels;
	}

}
