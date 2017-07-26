package com.haike.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Menu;

/**
 * @author xiaoming
 *
 */
@Repository
public interface MenuDao {
	public int addMenu(Menu menu);

	public int deleteMenu(@Param("id") String id);

	public Menu queryMenuById(@Param("id") String id);
	
	public List<Menu> queryMenus();
}
