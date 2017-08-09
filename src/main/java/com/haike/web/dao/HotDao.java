package com.haike.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Hot;

/**
 * @author xiaoming
 *
 */
@Repository
public interface HotDao {
	public int addHot(Hot hot);

	public int deleteHot(@Param("id") String id);

	public Hot queryHotById(@Param("id") String id);
	
	public List<Hot> queryHots();
	
	public List<Hot> queryHotsByType(@Param("type") int type);
}
