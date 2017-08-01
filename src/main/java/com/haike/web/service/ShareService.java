package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.ShareDao;
import com.haike.web.entity.Share;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming
 * @tags 热点分享service
 */
@Service
public class ShareService {

	public static final int STATUS_ADD_SUCCESS = 201;
	public static final int STATUS_ADD_FAILED_DB = 204;
	public static final int STATUS_DELETE_SUCCESS = 211;
	public static final int STATUS_DELETE_FAILED_DB = 212;
	public static final int STATUS_DELETE_FAILED_INEXISTENCE = 213;
	public static final int STATUS_UPDATE_FAILED_INEXISTENCE = 302;
	public static final int STATUS_UPDATE_SUCCESS = 303;
	public static final int STATUS_UPDATE_FAILED_DB = 304;

	@Resource
	private ShareDao shareDao;

	/**
	 * 新增分享热门站点信息
	 * 
	 * @param menuId
	 * @param userId
	 * @param link
	 * @param title
	 * @param content
	 * @return
	 */
	public int addShare(String menuId, String userId, String link, String title, String content) {
		Share share = new Share();
		share.setId(UUID.randomUUID().toString());
		share.setMenuId(menuId);
		share.setUserId(userId);
		share.setLink(link);
		share.setTitle(title);
		share.setContent(content);
		share.setCreateTime(new Date());
		share.setUpdateTime(new Date());
		int result = shareDao.addShare(share);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 删除一条分享信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteShare(String id) {
		Share queryShareById = queryShareById(id);
		if(queryShareById == null)
			return STATUS_DELETE_FAILED_INEXISTENCE;
		int result = shareDao.deleteShare(id);
		if (result == 1)
			return STATUS_DELETE_SUCCESS;
		else
			return STATUS_DELETE_FAILED_DB;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param id
	 * @param menuId
	 * @param userId
	 * @param link
	 * @param imgUrl
	 * @param title
	 * @param content
	 * @return
	 */
	public int updateShare(Share share) {
		int result = shareDao.updateShare(share);
		if (result == 1)
			return STATUS_UPDATE_SUCCESS;
		else
			return STATUS_UPDATE_FAILED_DB;
	}

	/**
	 * 通过用户id查询
	 * 
	 * @param id
	 * @return
	 */
	public Share queryShareById(String id) {
		return shareDao.queryShareById(id);
	}

	/**
	 * 通过menuId查询
	 * 
	 * @return
	 */
	public List<Share> querySharesByMenuId(String menuId) {
		return shareDao.querySharesByMenuId(menuId);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Share> queryShares() {
		return shareDao.queryShares();
	}

}
