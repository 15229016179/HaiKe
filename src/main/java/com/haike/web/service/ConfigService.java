package com.haike.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.haike.web.dao.datasource.ConfigDao;
import com.haike.web.dao.datasource_.ConfigDao_;
import com.haike.web.entity.Config;

/**
 * ��վ����
 * 
 * @author xiaoming
 * 
 */
@Service
public class ConfigService {

	@Resource
	private ConfigDao configDao;
	@Resource
	private ConfigDao_ configDao_;

	// ///////////////////////////////
	// ///// ���� ////////
	// ///////////////////////////////

	/**
	 * ��������
	 * 
	 * @param key
	 * @param value
	 * @return Config
	 */
	public Config addConfig(String key, String value) {
		Config config = new Config();
		config.setKey(key);
		config.setValue(value);
		config.setCreateTime(new Date());
		configDao.addConfig(config);
		return config;
	}

	// ///////////////////////////////
	// ///// �h�� ////////
	// ///////////////////////////////

	/**
	 * ɾ������
	 * 
	 * @param key
	 * @return Integer
	 */
	public int deleteConfigByKey(String key) {
		return configDao.deleteConfig(key);
	}

	// ///////////////////////////////
	// ///// �޸� ////////
	// ///////////////////////////////

	/**
	 * ��������
	 * 
	 * @param key
	 * @param value
	 */
	public Config updateConfigByKey(String key, String value) {
		Config config = configDao.getConfigByKey(key);
		config.setValue(value);
		configDao.updateConfig(config);
		Config config2 = configDao_.getConfigByKey(key);
		config2.setValue(value);
		configDao_.updateConfig(config2);
		this.getStringByKey(key);
		return config;
	}

	/**
	 * @param key
	 * @return
	 */
	@Cacheable(value="commonCache")
	public String getStringByKey(String key) {
		Config config = configDao.getConfigByKey(key);
		if (config == null) {
			return "";
		} else {
			return config.getValue();
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public int getIntKey(String key) {
		Config config = configDao.getConfigByKey(key);
		if (config == null) {
			return 0;
		} else {
			return Integer.parseInt(config.getValue());
		}
	}
}