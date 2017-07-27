package com.haike.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haike.web.entity.Menu;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.MenuService;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming 导航菜单
 */
@Controller
@RequestMapping("menu")
public class MenuAction {
	private static Logger logger = Logger.getLogger(MenuAction.class);

	@Autowired
	MenuService menuService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo add(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo res = new ResponseVo();
		String title = request.getParameter("title");
		String describe = request.getParameter("describe");
		String level = request.getParameter("level");
		String pid = request.getParameter("pid");
		logger.debug("MenuAction add title=" + title + ",describe=" + describe + ",level=" + level);
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(level)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int levelValue = 0;
		try {
			levelValue = Integer.parseInt(level);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int addMenuStatus = -1;
		switch (levelValue) {
		case 1:
			addMenuStatus = menuService.addMenu(title, describe, levelValue, null);
			break;
		case 2:
			if(StringUtils.isEmail(pid) || menuService.queryMenuById(pid) == null){
				response.setStatus(Status.BAD_REQUEST.getCode());
				res.setCode(Status.BAD_REQUEST.getCode());
				res.setMessage(Status.BAD_REQUEST.getMessage() + ",level为2时必须输入pid参数,即上一级菜单id");
				return res;
			}
			addMenuStatus = menuService.addMenu(title, describe, levelValue, pid);
			break;
		default:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",level必须为1|2");
			return res;
		}
		switch (addMenuStatus) {
		case MenuService.STATUS_ADD_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			break;
		case MenuService.STATUS_ADD_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存用户到数据库出错");
			break;
		}
		return res;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Menu> get(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<Menu> res = new ResponseVo<Menu>();
		String id = request.getParameter("id");
		logger.debug("MenuAction get id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}

		Menu menu = menuService.queryMenuById(id);
		if (menu == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",需要查询的菜单信息不存在");
		} else {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(menu);
		}
		return res;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Menu>> getAll(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("MenuAction getAll");
		ResponseVo<List<Menu>> res = new ResponseVo<List<Menu>>();
		List<Menu> queryMenus = menuService.queryMenus();
		if (queryMenus == null) {
			queryMenus = new ArrayList<Menu>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(queryMenus);
		return res;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.debug("MenuAction del id=" + id);
		ResponseVo res = new ResponseVo();
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int result = menuService.deleteMenu(id);
		switch (result) {
		case MenuService.STATUS_DELETE_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage() + ",删除成功");
			break;
		case MenuService.STATUS_DELETE_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",数据库出错");
			break;
		}
		return res;
	}

}
