package com.haike.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Feedback;

/**
 * @author xiaoming
 *
 */
@Repository
public interface FeedbackDao {
	public int addFeedback(Feedback feedback);

	public int deleteFeedback(@Param("id") String id);

	public Feedback queryFeedbackById(@Param("id") String id);
	
	public List<Feedback> queryFeedbacks();
}
