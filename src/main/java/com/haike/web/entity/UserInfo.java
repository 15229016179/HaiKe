package com.haike.web.entity;

import java.util.Date;

/**
 * @author xiaoming
 *
 * @tags 
 */
public class UserInfo extends IdEntity {
	private String userName;
	private String password;
	private String email;
	private Date createTime;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
