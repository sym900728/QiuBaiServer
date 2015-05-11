package com.qiubai.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiubai.dao.CharacterDao;
import com.qiubai.entity.Character;
import com.qiubai.tool.C3P0DBConnectionPool;
import com.qiubai.tool.ReadProperties;

public class CharacterDaoImpl implements CharacterDao {

	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean addCharacter(Character character) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();

		try {
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn,
					ReadProperties.read("sql", "addCharacter"),
					character.getUserid(), character.getChar_title(),
					character.getChar_context(), character.getChar_time());
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
	public boolean getCharacterByTitle(String char_title) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		Character character = null;
		try {
			character = queryRunner.query(conn,
					ReadProperties.read("sql", "getCharacterByTitle"),
					new BeanHandler<>(Character.class), char_title);
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
		if (character != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Character> getCharacter(int offsets, int rows) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();
		List<Character> characters = null;
		try {

			characters = queryRunner.query(conn,
					ReadProperties.read("sql", "getAllCharacter"),
					new BeanListHandler<>(Character.class), offsets, rows);

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
		return characters;
	}

	@Override
	public boolean addCharacterSupport(int id, String support, String tread) {
		Connection conn = (Connection) C3P0DBConnectionPool.getConnection();

		try {
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn,
					ReadProperties.read("sql", "addCharacterSupportTread"), support,
					tread, id);
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

}
