package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.BannerDao;
import com.haike.web.entity.Banner;

/**
 * @author xiaoming
 * @tags	banner service
 */
@Service
public class BannerService {

	public static final int STATUS_ADD_SUCCESS = 201;
	public static final int STATUS_ADD_FAILED_DB = 204;
	public static final int STATUS_DELETE_SUCCESS = 211;
	public static final int STATUS_DELETE_FAILED_DB = 212;

	@Resource
	private BannerDao bannerDao;

	/**
	 * 增加banner
	 * 
	 * @param title
	 * @param describe
	 * @param imgUrl
	 * @param smallImgUrl
	 * @param link
	 * @param no
	 * @return
	 */
	public int addBanner(String title, String describe, String imgUrl, String smallImgUrl, String link, int no) {
		Banner banner = new Banner();
		banner.setId(UUID.randomUUID().toString());
		banner.setTitle(title);
		banner.setDescribe(describe);
		banner.setImgUrl(imgUrl);
		banner.setSmallImgUrl(smallImgUrl);
		banner.setLink(link);
		banner.setNo(no);
		banner.setCreateTime(new Date());
		int result = bannerDao.addBanner(banner);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 删除一条banner
	 * 
	 * @param id
	 * @return
	 */
	public int deleteBanner(String id) {
		int result = bannerDao.deleteBanner(id);
		if (result == 1)
			return STATUS_DELETE_SUCCESS;
		else
			return STATUS_DELETE_FAILED_DB;
	}

	/**
	 * 通过用户id查询banner
	 * 
	 * @param id
	 * @return
	 */
	public Banner queryBannerById(String id) {
		return bannerDao.queryBannerById(id);
	}

	/**
	 * 查询所有banner
	 * 
	 * @return
	 */
	public List<Banner> queryBanners() {
		return bannerDao.queryBanners();
	}

}
