package com.haike.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haike.web.dao.FeedbackDao;
import com.haike.web.entity.Feedback;

/**
 * @author xiaoming
 * @tags	热点分享service
 */
@Service
public class ShareService {

	public static final int STATUS_ADD_SUCCESS = 201;
	public static final int STATUS_ADD_FAILED_DB = 204;
	public static final int STATUS_DELETE_SUCCESS = 211;
	public static final int STATUS_DELETE_FAILED_DB = 212;

	@Resource
	private FeedbackDao feedbackDao;

	/**
	 * 增加反馈信息
	 * 
	 * @param name
	 * @param userId
	 * @param email
	 * @param title
	 * @param content
	 * @return
	 */
	public int addFeedback(String name, String userId, String email, String title, String content) {
		Feedback feedback = new Feedback();
		feedback.setId(UUID.randomUUID().toString());
		feedback.setName(name);
		feedback.setUserId(userId);
		feedback.setEmail(email);
		feedback.setTitle(title);
		feedback.setContent(content);
		feedback.setCreateTime(new Date());
		int result = feedbackDao.addFeedback(feedback);
		if (result == 1)
			return STATUS_ADD_SUCCESS;
		else
			return STATUS_ADD_FAILED_DB;
	}

	/**
	 * 删除一条反馈
	 * 
	 * @param id
	 * @return
	 */
	public int deleteFeedback(String id) {
		int result = feedbackDao.deleteFeedback(id);
		if (result == 1)
			return STATUS_DELETE_SUCCESS;
		else
			return STATUS_DELETE_FAILED_DB;
	}

	/**
	 * 通过用户id查询反馈信息
	 * 
	 * @param id
	 * @return
	 */
	public Feedback queryFeedbackById(String id) {
		return feedbackDao.queryFeedbackById(id);
	}

	/**
	 * 查询所有反馈信息
	 * 
	 * @return
	 */
	public List<Feedback> queryFeedbacks() {
		return feedbackDao.queryFeedbacks();
	}

}
