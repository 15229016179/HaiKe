/*



 */

package com.haike.web.dao.datasource_;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.haike.web.entity.Config;

/**
 * ��վ����
 * 
 * @author xiaoming
 * 
 */

@Repository("configdao_")
public interface ConfigDao_ {

	// ///////////////////////////////
		// ///// ���� ////////
		// ///////////////////////////////

		/**
		 * ��������
		 * 
		 * @return Integer
		 */
		public int addConfig(Config config);

		// ///////////////////////////////
		// ///// �h�� ////////
		// ///////////////////////////////

		/**
		 * ɾ������
		 * 
		 * return Integer
		 */
		public int deleteConfig(@Param("key") String key);

		// ///////////////////////////////
		// ///// �޸� ////////
		// ///////////////////////////////

		/**
		 * ��������
		 * 
		 * @return Integer
		 */
		public int updateConfig(Config config);

		// ///////////////////////////////
		// ///// ��ԃ ////////
		// ///////////////////////////////

		/**
		 * �鿴����
		 * 
		 * @return Config
		 */
		public Config getConfigByKey(@Param("key") String key);
	}
