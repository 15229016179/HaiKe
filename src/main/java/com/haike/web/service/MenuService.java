package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.MenuDao;
import com.haike.web.entity.Menu;

/**
 * @author xiaoming
 * @tags	导航菜单service
 */
@Service
public class MenuService {

	public static final int STATUS_ADD_SUCCESS = 201;
	public static final int STATUS_ADD_FAILED_DB = 204;
	public static final int STATUS_DELETE_SUCCESS = 211;
	public static final int STATUS_DELETE_FAILED_DB = 212;

	@Resource
	private MenuDao menuDao;

	/**
	 * 增加导航菜单信息
	 * 
	 * @param title
	 * @param describe
	 * @param level
	 * @param pid
	 * @return
	 */
	public int addMenu(String title, String describe, int level, String pid) {
		Menu menu = new Menu();
		menu.setId(UUID.randomUUID().toString());
		menu.setTitle(title);
		menu.setDescribe(describe);
		menu.setLevel(level);
		menu.setPid(pid);
		menu.setCreateTime(new Date());
		int result = menuDao.addMenu(menu);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 删除一条导航菜单信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteMenu(String id) {
		int result = menuDao.deleteMenu(id);
		if (result == 1)
			return STATUS_DELETE_SUCCESS;
		else
			return STATUS_DELETE_FAILED_DB;
	}

	/**
	 * 通过用户id查询导航菜单信息
	 * 
	 * @param id
	 * @return
	 */
	public Menu queryMenuById(String id) {
		return menuDao.queryMenuById(id);
	}

	/**
	 * 查询所有导航菜单
	 * 
	 * @return
	 */
	public List<Menu> queryMenus() {
		return menuDao.queryMenus();
	}

}
