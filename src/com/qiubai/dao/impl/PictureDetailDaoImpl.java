package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.qiubai.dao.PictureDetailDao;
import com.qiubai.entity.Picture;
import com.qiubai.entity.PictureDetail;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class PictureDetailDaoImpl implements PictureDetailDao {

	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean addPictureDetail(PictureDetail pictureDetail) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();

		try {
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn,
					ReadProperties.read("sql", "addPictureDetail"),
					pictureDetail.getPic_id(), pictureDetail.getId(),
					pictureDetail.getPic_address(),
					pictureDetail.getPic_describe());
			if (ret > 0) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	@Override
	public boolean getPictureById(int id) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		PictureDetail pictureDetail = null;
		try {
			pictureDetail = queryRunner.query(conn,
					ReadProperties.read("sql", "getPictureDetailById"),
					new BeanHandler<>(PictureDetail.class), id);
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
		if (pictureDetail != null) {
			return true;
		}
		return false;
	}

}
