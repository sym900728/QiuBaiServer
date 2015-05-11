package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.PictureDao;
import com.qiubai.entity.Character;
import com.qiubai.entity.Picture;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class PictureDaoImpl implements PictureDao {

	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean addPicture(Picture picture) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();

		try {
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn,
					ReadProperties.read("sql", "addPicture"), picture.getId(),
					picture.getUser_id(), picture.getPic_title(),
					picture.getPic_time(),picture.getPic_extra());
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
	public List<Picture> getAllPicture() {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Picture> pictures = null;
		try {

			pictures = queryRunner.query(conn,
					ReadProperties.read("sql", "getAllPicture"),
					new BeanListHandler<>(Picture.class));

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
		return pictures;
	}

	@Override
	public boolean getPictureByTitle(String pic_title) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		Picture picture = null;
		try {
			picture = queryRunner.query(conn,
					ReadProperties.read("sql", "getPictureByTitle"),
					new BeanHandler<>(Picture.class), pic_title);
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
		if (picture != null) {
			return true;
		}
		return false;
	}

}
