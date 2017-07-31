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

import com.haike.web.entity.Feedback;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.FeedbackService;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming 反馈信息
 */
@Controller
@RequestMapping("share")
public class ShareAction {
	private static Logger logger = Logger.getLogger(ShareAction.class);

	@Autowired
	FeedbackService feedbackService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo add(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo res = new ResponseVo();
		String userId = request.getParameter("userId");
		String menuId = request.getParameter("menuId");
		String link = request.getParameter("link");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		logger.debug("FeedbackService add userId=" + userId + ",menuId=" + menuId + ",link=" + link + ",title=" + title
				+ ",content=" + content);
		if (StringUtils.isEmpty(link) || StringUtils.isEmpty(menuId) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int addFeedbackStatus = feedbackService.addFeedback(name, userId, email, title, content);
		switch (addFeedbackStatus) {
		case FeedbackService.STATUS_ADD_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			break;
		case FeedbackService.STATUS_ADD_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存用户到数据库出错");
			break;
		}
		return res;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Feedback> get(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<Feedback> res = new ResponseVo<Feedback>();
		String id = request.getParameter("id");
		logger.debug("FeedbackService get id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}

		Feedback feedback = feedbackService.queryFeedbackById(id);
		if (feedback == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",需要查询的反馈信息不存在");
		} else {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(feedback);
		}
		return res;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Feedback>> getAll(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("FeedbackService getAll");
		ResponseVo<List<Feedback>> res = new ResponseVo<List<Feedback>>();
		List<Feedback> queryFeedbacks = feedbackService.queryFeedbacks();
		if(queryFeedbacks == null){
			queryFeedbacks = new ArrayList<Feedback>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(queryFeedbacks);
		return res;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.debug("FeedbackService del id=" + id);
		ResponseVo res = new ResponseVo();
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int result = feedbackService.deleteFeedback(id);
		switch (result) {
		case FeedbackService.STATUS_DELETE_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage() + ",删除成功");
			break;
		case FeedbackService.STATUS_DELETE_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",数据库出错");
			break;
		}
		return res;
	}

}
