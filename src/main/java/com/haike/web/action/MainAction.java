package com.haike.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haike.web.entity.UserInfo;

@Controller
public class MainAction {
	private static Logger logger = Logger.getLogger(MainAction.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public UserInfo home(HttpServletRequest request, ModelMap modelMap, String requestUrlString) {
		logger.debug("MainAction home");
		return new UserInfo();
	}

}
