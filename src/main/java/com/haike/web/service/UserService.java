package com.haike.web.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.haike.web.dao.UserDao;
import com.haike.web.dao.UserDaoImpl;
import com.haike.web.entity.UserInfo;

/**
 * @author xiaoming
 *
 * @tags
 */
public class UserService {
	UserDao userDao = new UserDaoImpl();

	public boolean checkUser(Connection conn, UserInfo userInfo)
			throws SQLException {
		Boolean status = false;
		try {
			conn.setAutoCommit(false);
			status = userDao.queryUser(conn, userInfo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return status;
	}

}
