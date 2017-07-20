/**  
 * @Title: ClassUtils.java 
 * @Package com.warmdoctor.ows.core.util 
 * @author bruce  
 * @date 2015年4月29日 下午10:41:00 
 * @version V1.0  
 */
package com.haike.web.util;

/**
 * 类对象判断工具
 * 
 * @ClassName: ClassUtils
 * @author bruce
 * @date 2015年4月29日 下午10:41:00
 */
public class ClassUtils {

	/**
	 * 判断对象是否属于常见数据类型
	 * 
	 * @author :bruce
	 * @param cls
	 * @return boolean
	 * @create 2015年4月29日下午10:42:35
	 */
	public static final boolean isBasisClass(Class<?> cls) {
		return cls.isPrimitive() || cls.equals(String.class);
	}

	/**
	 * 判断是否属于
	 * 
	 * @author :bruce
	 * @param cls
	 * @return boolean
	 * @create 2015年4月29日下午11:58:07
	 */
	public static boolean isClass(Class<?> cls) {
		return cls.equals(Class.class);
	}
	
}
