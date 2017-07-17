package com.haike.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haike.web.entity.UserInfo;
import com.haike.web.service.UserService;

@Controller
@RequestMapping("/")
public class UserAction {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public UserInfo login(HttpServletRequest request, ModelMap modelMap, String requestUrlString) {
		UserInfo user = userService.queryUserByUserName("小明");
		return user;
	}

}
