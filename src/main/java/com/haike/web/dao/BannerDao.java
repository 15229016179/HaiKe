package com.haike.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Banner;

/**
 * @author xiaoming
 *
 */
@Repository
public interface BannerDao {
	public int addBanner(Banner banner);

	public int deleteBanner(@Param("id") String id);

	public Banner queryBannerById(@Param("id") String id);
	
	public List<Banner> queryBanners();
}
