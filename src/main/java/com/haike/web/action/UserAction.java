package com.haike.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.haike.web.entity.UserInfo;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.UserService;
import com.haike.web.util.SecurityUtils;

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
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("用户名不存在");
			return res;
		}
		if (SecurityUtils.sha1(password).equals(user.getPassword())) {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(user);
		} else {
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage("输入的密码不匹配");
		}
		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public UserInfo register(HttpServletRequest request, ModelMap modelMap, String requestUrlString) {
		logger.debug("UserAction login");
		UserInfo user = userService.queryUserByUserName("小明");
		return user;
	}

}
