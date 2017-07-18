package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.UserDao;
import com.haike.web.entity.UserInfo;

/**
 * @author xiaoming
 *
 * @tags
 */
@Service
public class UserService {

	public static final int STATUS_ADD_SUCCESS = 101;
	public static final int STATUS_ADD_FAILED_EXISTED_USERNAME = 102;
	public static final int STATUS_ADD_FAILED_EXISTED_EMAIL = 103;
	public static final int STATUS_ADD_FAILED_DB = 104;
	public static final int STATUS_UPDATE_SUCCESS = 111;
	public static final int STATUS_UPDATE_FAILED_INEXISTENCE_USERNAME = 112;
	public static final int STATUS_UPDATE_FAILED_INEXISTENCE_EMAIL = 113;

	@Resource
	private UserDao userDao;

	/**
	 * 增加用户
	 * 
	 * @param email
	 * @param userName
	 * @param password
	 * @return
	 */
	public int addUser(String email, String userName, String password) {
		UserInfo user = userDao.queryUserByUserName(userName);
		if (user != null)
			return STATUS_ADD_FAILED_EXISTED_USERNAME;
		user = userDao.queryUserByEmail(email);
		if (user != null)
			return STATUS_ADD_FAILED_EXISTED_EMAIL;
		user = new UserInfo();
		user.setId(UUID.randomUUID().toString());
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(userName);
		user.setCreateTime(new Date());
		int result = userDao.saveUser(user);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param email
	 * @param userName
	 * @param password
	 * @return
	 */
	public int updateUserPassword(String email, String userName, String password) {
		UserInfo user = userDao.queryUserByUserNameAndEmail(userName, email);
		if (user == null) {
			user = userDao.queryUserByEmail(email);
			if (user == null)
				return STATUS_UPDATE_FAILED_INEXISTENCE_EMAIL;
			else
				return STATUS_UPDATE_FAILED_INEXISTENCE_USERNAME;
		}
		user.setPassword(password);
		int result = userDao.updateUser(user);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 通过用户昵称查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo queryUserByUserName(String userName) {
		return userDao.queryUserByUserName(userName);
	}

	/**
	 * 通过用户邮箱查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo queryUserByEmail(String email) {
		return userDao.queryUserByEmail(email);
	}

	/**
	 * 通过用户id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo queryUserById(String id) {
		return userDao.queryUserById(id);
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @param id
	 * @return
	 */
	public List<UserInfo> queryUsers() {
		return userDao.queryUsers();
	}

}
