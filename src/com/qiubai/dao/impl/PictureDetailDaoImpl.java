package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.PictureDetailDao;
import com.qiubai.entity.Picture;
import com.qiubai.entity.PictureDetail;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class PictureDetailDaoImpl implements PictureDetailDao {

	QueryRunner runner = new QueryRunner();

	@Override
	public List<PictureDetail> getPictureDetails(int pictureid) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<PictureDetail> pictureDetails = null;

		try {
			pictureDetails = runner.query(conn, ReadProperties.read("sql", "getPictureDetails"),
					new BeanListHandler<>(PictureDetail.class), pictureid);
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
		return pictureDetails;
	}

}
