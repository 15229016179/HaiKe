package com.haike.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haike.web.entity.Share;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.ShareService;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming 资源分享
 */
@Controller
@RequestMapping("share")
public class ShareAction {
	private static Logger logger = Logger.getLogger(ShareAction.class);

	@Autowired
	ShareService shareService;

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
		logger.debug("ShareAction add userId=" + userId + ",menuId=" + menuId + ",link=" + link + ",title=" + title
				+ ",content=" + content);
		if (StringUtils.isEmpty(link) || StringUtils.isEmpty(menuId) || StringUtils.isEmpty(title)
				|| StringUtils.isEmpty(content)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int addShareStatus = shareService.addShare(menuId, userId, link, title, content);
		switch (addShareStatus) {
		case ShareService.STATUS_ADD_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			break;
		case ShareService.STATUS_ADD_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存到数据库出错");
			break;
		}
		return res;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo update(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo res = new ResponseVo();
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		String menuId = request.getParameter("menuId");
		String link = request.getParameter("link");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String imgUrl = request.getParameter("imgUrl");
		logger.debug("ShareAction update id=" + id + ",userId=" + userId + ",menuId=" + menuId + ",link=" + link
				+ ",title=" + title + ",content=" + content + ",imgUrl" + imgUrl);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage() + ",id为必填参数");
			return res;
		}
		Share share = shareService.queryShareById(id);
		if (share == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",不存在的id");
			return res;
		}
		if(!StringUtils.isEmpty(menuId))
			share.setMenuId(menuId);
		if(!StringUtils.isEmpty(userId))
			share.setUserId(userId);
		if(!StringUtils.isEmpty(link))
			share.setLink(link);
		if(!StringUtils.isEmpty(imgUrl))
			share.setImgUrl(imgUrl);
		if(!StringUtils.isEmpty(title))
			share.setTitle(title);
		if(!StringUtils.isEmpty(content))
			share.setContent(content);
		share.setUpdateTime(new Date());
		int updateShareStatus = shareService.updateShare(share);
		switch (updateShareStatus) {
		case ShareService.STATUS_UPDATE_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			break;
		case ShareService.STATUS_UPDATE_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",保存到数据库出错");
			break;
		}
		return res;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Share> get(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<Share> res = new ResponseVo<Share>();
		String id = request.getParameter("id");
		logger.debug("ShareAction get id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		Share share = shareService.queryShareById(id);
		if (share == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",需要查询的信息不存在");
		} else {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(share);
		}
		return res;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Share>> getAll(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("ShareAction getAll");
		ResponseVo<List<Share>> res = new ResponseVo<List<Share>>();
		List<Share> shares = shareService.queryShares();
		if (shares == null) {
			shares = new ArrayList<Share>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(shares);
		return res;
	}

	@RequestMapping(value = "/getByMenuId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<List<Share>> getByMenuId(HttpServletRequest request, HttpServletResponse response) {
		String menuId = request.getParameter("menuId");
		logger.debug("ShareAction getByMenuId menuId=" + menuId);
		ResponseVo<List<Share>> res = new ResponseVo<List<Share>>();
		List<Share> shares = shareService.querySharesByMenuId(menuId);
		if (shares == null) {
			shares = new ArrayList<Share>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(shares);
		return res;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.debug("ShareAction del id=" + id);
		ResponseVo res = new ResponseVo();
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int result = shareService.deleteShare(id);
		switch (result) {
		case ShareService.STATUS_DELETE_SUCCESS:
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage() + ",删除成功");
			break;
		case ShareService.STATUS_DELETE_FAILED_DB:
			response.setStatus(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setCode(Status.INTERNAL_SERVER_ERROR_DB.getCode());
			res.setMessage(Status.INTERNAL_SERVER_ERROR_DB.getMessage() + ",数据库出错");
			break;
		case ShareService.STATUS_DELETE_FAILED_INEXISTENCE:
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",不存在的id");
			break;
		}
		return res;
	}

}
