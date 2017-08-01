package com.haike.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Share;

/**
 * @author xiaoming
 *
 */
@Repository
public interface ShareDao {
	public int addShare(Share share);

	public int deleteShare(@Param("id") String id);
	
	public int updateShare(Share share);

	public Share queryShareById(@Param("id") String id);
	
	public List<Share> queryShares();
	
	public List<Share> querySharesByMenuId(@Param("menuId") String menuId);
}
