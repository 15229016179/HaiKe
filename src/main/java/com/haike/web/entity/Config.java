package com.haike.web.entity;

import java.util.Date;

/**
 * ��վ����ʵ��
 * 
 * @author xiaoming
 * 
 */

public class Config {

	/**
	 * key
	 */
	private String key;

	/**
	 * ֵ
	 */
	private String value;
	/**
	 * ����
	 */
	private String description;

	/**
	 * ʱ��
	 */
	private Date createTime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
