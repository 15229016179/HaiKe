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

import com.haike.web.entity.Hot;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.FeedbackService;
import com.haike.web.service.HotService;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming banner
 */
@Controller
@RequestMapping("hot")
public class HotAction {
	private static Logger logger = Logger.getLogger(HotAction.class);

	@Autowired
	HotService hotService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo add(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo res = new ResponseVo();
		String shareId = request.getParameter("shareId");
		String image = request.getParameter("image");
		String typeStr = request.getParameter("type");
		logger.debug("HotAction add shareId=" + shareId + ",image=" + image + ",typeStr=" + typeStr);
		if (StringUtils.isEmpty(shareId) || StringUtils.isEmpty(image) || StringUtils.isEmpty(typeStr)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int type = 0;
		if (!StringUtils.isEmpty(typeStr)) {
			try {
				type = Integer.parseInt(typeStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int addHotStatus = hotService.addHot(shareId, image, type);
		switch (addHotStatus) {
		case FeedbackService.STATUS_ADD_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			break;
		case FeedbackService.STATUS_ADD_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存到数据库出错");
			break;
		}
		return res;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Hot> get(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<Hot> res = new ResponseVo<Hot>();
		String id = request.getParameter("id");
		logger.debug("HotAction get id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}

		Hot hot = hotService.queryHotById(id);
		if (hot == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",需要查询的反馈信息不存在");
		} else {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(hot);
		}
		return res;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Hot>> getAll(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("HotAction getAll");
		ResponseVo<List<Hot>> res = new ResponseVo<List<Hot>>();
		List<Hot> queryHots = hotService.queryHots();
		if (queryHots == null) {
			queryHots = new ArrayList<Hot>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(queryHots);
		return res;
	}
	
	@RequestMapping(value = "/getByType", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Hot>> getByType(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<List<Hot>> res = new ResponseVo<List<Hot>>();
		String typeStr = request.getParameter("type");
		logger.debug("HotAction getByType typeStr=" + typeStr);
		if (StringUtils.isEmpty(typeStr)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int type = 0;
		if (!StringUtils.isEmpty(typeStr)) {
			try {
				type = Integer.parseInt(typeStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		List<Hot> queryHots = hotService.queryHotsByType(type);
		if (queryHots == null) {
			queryHots = new ArrayList<Hot>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(queryHots);
		return res;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.debug("HotAction del id=" + id);
		ResponseVo res = new ResponseVo();
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int result = hotService.deleteHot(id);
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
