/**
 * @Title: NumberRandomUtil.java
 * @Package com.warmdoctor.ows.core.util
 * @author bruce
 * @date 2015年5月8日 下午11:16:02
 * @version V1.0
 */
package com.haike.web.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 随机数
 *
 * @ClassName: NumberRandomUtil
 * @author bruce
 * @date 2015年5月8日 下午11:16:02
 */
public class NumberRandomUtil {

	/**
	 * @Fields DEFAULT_NUM : 默认的数据
	 */
	private static final String[] DEFAULT_NUM = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

	/**
	 * @Fields numbers : 随机数列表
	 */
	private static List<String> numbers = Arrays.asList(DEFAULT_NUM);

	/**
	 * @Fields DEFAULT_DIGIT : 默认4位
	 */
	private static final int DEFAULT_DIGIT = 4;

	/**
	 * 获取验证码信息
	 *
	 * @author :bruce
	 * @return String
	 * @create 2015年5月8日下午11:20:18
	 */
	public static String getAuth() {
		return generate(DEFAULT_DIGIT);
	}

	/**
	 * 生成几位数的随机数
	 *
	 * @author :bruce
	 * @param digit
	 * @return String
	 * @create 2015年5月8日下午11:18:33
	 */
	public static String generate(int digit) {
		if (digit < 1) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < (digit / 10) + 1; j++) {
			Collections.shuffle(numbers);
			for (int i = 0; i < numbers.size(); i++) {
				sb.append(numbers.get(i));
			}
		}
		return sb.substring(3, digit + 3);
	}

	/**
	 * 随机产生10位优惠码
	 *
	 * @return
	 */
	public static String makeRandom() {
		Random select = new Random();
		// 更改数字可以选择随机出现的字符串长度
		int nlr = 0;
		char nt = ' ';
		StringBuffer target = new StringBuffer(50);
		out: for (int i = 0; i < 10;) {
			// 这里更改数字可以决定出现的字符是出现在什么字符之间
			nlr = select.nextInt(90);
			int j = 1;
			if ((nlr > 49 && nlr < 58) || nlr > 65) {
				if (nlr > 49 && nlr < 58) {
					j++;
				}
				if (j == 4)
					continue out;
				nt = (char) nlr;
				target.append(nt);
				i++;
			} else {
				continue;
			}
		}
		return target.toString();
	}
  
	
	/**
	 * 生成6位随机数
	 */
	public static int generate() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++)
			result = result * 10 + array[i];
		return result;
	}
}
