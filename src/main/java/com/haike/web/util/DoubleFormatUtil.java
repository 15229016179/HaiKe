/**  
 * @Title: DoubleFormatUtil.java 
 * @Package com.warmdoctor.ows.common.util
 * @author bruce  
 * @date 2015年4月27日 上午10:31:40 
 * @version V1.0  
 */ 
package com.haike.web.util;

import java.text.DecimalFormat;

/** 
 * 小数点保留工具
 * @ClassName: DoubleFormatUtil 
 * @author bruce 
 * @date 2015年4月27日 上午10:31:40  
 */
public class DoubleFormatUtil {
	
	/** 
	 * 格式转换
	 * @author :bruce
	 * @param patten
	 * @param dle
	 * @return double    
	 * @create 2015年4月16日下午2:50:20
	 */
	public static final double format(String patten, double dle) {
		if (patten == null || "".equals(patten) || "null".equals(patten)) {
			patten = "###.00";
		}
		return Double.parseDouble(new DecimalFormat(patten).format(dle));
	}
	
}
