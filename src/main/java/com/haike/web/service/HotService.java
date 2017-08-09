package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.HotDao;
import com.haike.web.entity.Hot;

/**
 * @author xiaoming
 * @tags	首页热门推荐和站长推荐 service
 */
@Service
public class HotService {

	public static final int STATUS_ADD_SUCCESS = 201;
	public static final int STATUS_ADD_FAILED_DB = 204;
	public static final int STATUS_DELETE_SUCCESS = 211;
	public static final int STATUS_DELETE_FAILED_DB = 212;

	@Resource
	private HotDao hotDao;

	/**
	 * 增加hot
	 * 
	 * @param shareId
	 * @param image
	 * @param type
	 * @return
	 */
	public int addHot(String shareId, String image, int type) {
		Hot hot = new Hot();
		hot.setId(UUID.randomUUID().toString());
		hot.setShareId(shareId);
		hot.setType(type);
		hot.setCreateTime(new Date());
		int result = hotDao.addHot(hot);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 删除一条hot
	 * 
	 * @param id
	 * @return
	 */
	public int deleteHot(String id) {
		int result = hotDao.deleteHot(id);
		if (result == 1)
			return STATUS_DELETE_SUCCESS;
		else
			return STATUS_DELETE_FAILED_DB;
	}

	/**
	 * 通过用户id查询hot
	 * 
	 * @param id
	 * @return
	 */
	public Hot queryHotById(String id) {
		return hotDao.queryHotById(id);
	}

	/**
	 * 通过类型查询hot
	 * 
	 * @param type 热门类型 1：站长推荐；2：热门推荐
	 * @return
	 */
	public List<Hot> queryHotsByType(int type) {
		return hotDao.queryHotsByType(type);
	}
	
	/**
	 * 查询所有hot
	 * 
	 * @return
	 */
	public List<Hot> queryHots() {
		return hotDao.queryHots();
	}

}
