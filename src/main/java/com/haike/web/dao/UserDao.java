package com.haike.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.haike.web.entity.UserAddr;
import com.haike.web.entity.UserInfo;

/**
 * @author xiaoming
 *
 */
public interface UserDao {
	public void save(Connection conn, UserAddr userAddr) throws SQLException;

	public void update(Connection conn, UserAddr userAddr) throws SQLException;

	public void delete(Connection conn, long id) throws SQLException;

	public UserAddr singalSelect(Connection conn, long id) throws SQLException;

	public boolean queryUser(Connection conn, UserInfo userInfo)
			throws SQLException;
}
