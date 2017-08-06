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

import com.haike.web.entity.Banner;
import com.haike.web.entity.response.ResponseVo;
import com.haike.web.entity.response.Status;
import com.haike.web.service.BannerService;
import com.haike.web.service.FeedbackService;
import com.haike.web.util.StringUtils;

/**
 * @author xiaoming banner
 */
@Controller
@RequestMapping("banner")
public class BannerAction {
	private static Logger logger = Logger.getLogger(BannerAction.class);

	@Autowired
	BannerService bannerService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo add(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo res = new ResponseVo();
		String title = request.getParameter("title");
		String describe = request.getParameter("describe");
		String imgUrl = request.getParameter("imgUrl");
		String smallImgUrl = request.getParameter("smallImgUrl");
		String link = request.getParameter("link");
		String noStr = request.getParameter("no");
		logger.debug("BannerAction add title=" + title + ",describe=" + describe + ",imgUrl=" + imgUrl + ",smallImgUrl="
				+ smallImgUrl + ",link=" + link + ",no=" + noStr);
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(imgUrl) || StringUtils.isEmpty(smallImgUrl) || StringUtils.isEmpty(link)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int no = 0;
		if (!StringUtils.isEmpty(noStr)) {
			try {
				no = Integer.parseInt(noStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int addBannerStatus = bannerService.addBanner(title, describe, imgUrl, smallImgUrl, link, no);
		switch (addBannerStatus) {
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
	public ResponseVo<Banner> get(HttpServletRequest request, HttpServletResponse response) {
		ResponseVo<Banner> res = new ResponseVo<Banner>();
		String id = request.getParameter("id");
		logger.debug("BannerAction get id=" + id);
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}

		Banner banner = bannerService.queryBannerById(id);
		if (banner == null) {
			response.setStatus(Status.BAD_REQUEST_PARAMS.getCode());
			res.setCode(Status.BAD_REQUEST_PARAMS.getCode());
			res.setMessage(Status.BAD_REQUEST_PARAMS.getMessage() + ",需要查询的反馈信息不存在");
		} else {
			res.setCode(Status.OK.getCode());
			res.setMessage(Status.OK.getMessage());
			res.setResult(banner);
		}
		return res;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<List<Banner>> getAll(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("BannerAction getAll");
		ResponseVo<List<Banner>> res = new ResponseVo<List<Banner>>();
		List<Banner> queryBanners = bannerService.queryBanners();
		if (queryBanners == null) {
			queryBanners = new ArrayList<Banner>();
		}
		res.setCode(Status.OK.getCode());
		res.setMessage(Status.OK.getMessage());
		res.setResult(queryBanners);
		return res;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.debug("BannerAction del id=" + id);
		ResponseVo res = new ResponseVo();
		if (StringUtils.isEmpty(id)) {
			response.setStatus(Status.BAD_REQUEST.getCode());
			res.setCode(Status.BAD_REQUEST.getCode());
			res.setMessage(Status.BAD_REQUEST.getMessage());
			return res;
		}
		int result = bannerService.deleteBanner(id);
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
