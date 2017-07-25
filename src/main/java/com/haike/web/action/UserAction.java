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

import com.haike.web.entity.UserInfo;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.UserService;
import com.haike.web.util.SecurityUtils;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming
 *	用户
 */
@Controller
@RequestMapping("user")
public class UserAction {
	private static Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<UserInfo> login(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<UserInfo> res = new ResponseVo<UserInfo>();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		logger.debug("UserAction login userName=" + userName + ",password=" + password);
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		UserInfo user = userService.queryUserByUserName(userName);
		if (user == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("用户名不存在");
			return res;
		}
		if (SecurityUtils.sha1(password).equals(user.getPassword())) {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(user);
		} else {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("输入的密码不匹配");
		}
		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<UserInfo> register(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<UserInfo> res = new ResponseVo<UserInfo>();
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		logger.debug("UserAction register email=" + email + ",userName=" + userName + ",password=" + password);
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		if (!StringUtils.isEmail(email)) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",邮箱格式不正确");
			return res;
		}
		int addUserStatus = userService.addUser(email, userName, SecurityUtils.sha1(password));
		switch (addUserStatus) {
		case UserService.STATUS_ADD_SUCCESS:
			UserInfo user = userService.queryUserByUserName(userName);
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(user);
			break;
		case UserService.STATUS_ADD_FAILED_EXISTED_USERNAME:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("该用户名已存在");
			break;
		case UserService.STATUS_ADD_FAILED_EXISTED_EMAIL:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("该邮箱已注册");
			break;
		default:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存用户到数据库出错");
			break;
		}
		return res;
	}

	@RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<UserInfo> changePassword(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<UserInfo> res = new ResponseVo<UserInfo>();
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		logger.debug("UserAction changePasswd email=" + email + ",userName=" + userName + ",password=" + password);
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		if (!StringUtils.isEmail(email)) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",邮箱格式不正确");
			return res;
		}
		int updateUserPassword = userService.updateUserPassword(email, userName, SecurityUtils.sha1(password));
		switch (updateUserPassword) {
		case UserService.STATUS_UPDATE_FAILED_INEXISTENCE_EMAIL:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("未注册该邮箱");
			break;
		case UserService.STATUS_UPDATE_FAILED_INEXISTENCE_USERNAME:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("用户名不存在");
			break;
		case UserService.STATUS_UPDATE_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage() + ",更改密码成功，请重新登录");
			break;
		default:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",操作数据库出错");
		}
		return res;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<UserInfo> getUser(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<UserInfo> res = new ResponseVo<UserInfo>();
		String id = request.getParameter("id");
		logger.debug("UserAction login id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		UserInfo user = userService.queryUserById(id);
		if (user == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",查询不到用户信息");
		}else{
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(user);
		}
		return res;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<UserInfo>> getAllUser(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<List<UserInfo>> res = new ResponseVo<List<UserInfo>>();
		List<UserInfo> users = userService.queryUsers();
		if(users == null){
			users = new ArrayList<UserInfo>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(users);
		return res;
	}

}
