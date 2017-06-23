package com.haike.web.redis.datasource;

import redis.clients.jedis.ShardedJedis;


/**
 * 
 * @ClassName: RedisDataSource
 * @Description: TODO(redis�ӿ�)
 * @author xiaoming
 * @date 2016��5��25�� ����3:10:57
 *
 */
public interface RedisDataSource {
	public abstract ShardedJedis getRedisClient();

	/**
	 * 
	* @Title: returnResource 
	* @Description: TODO(���ᵽ���ӳ�) 
	* @param @param shardedJedis    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void returnResource(ShardedJedis shardedJedis);

	/**
	 * false:�������ӳ�;true:�������ӳ�
	* @Title: returnResource 
	* @Description: TODO(false:�������ӳ�;true:�������ӳ�) 
	* @param @param shardedJedis
	* @param @param broken    �趨�ļ� 
	* @return void    �������� 
	* @throws
	 */
	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}