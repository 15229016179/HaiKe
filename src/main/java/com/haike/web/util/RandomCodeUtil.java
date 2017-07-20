package  com.haike.web.util;

import java.util.Random;

/**
 * 随机验证码工具类
 * 
 * @author Arvin.Cao
 *
 */
public class RandomCodeUtil {

	/**
	 * 获取手机短信验证码
	 * 
	 * @param num
	 *            验证码位数
	 * @return
	 */
	public static String getPhoneValidate(int num) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	/**
	 * 随机产生10位优惠码
	 * 
	 * @return
	 */
	public static String makeRandom() {
		Random select = new Random();
		// 更改数字可以选择随机出现的字符串长度
		int nowletter = 0;
		char nowlet = ' ';
		StringBuffer target = new StringBuffer(50);

		out: for (int i = 0; i < 10;) {
			// 这里更改数字可以决定出现的字符是出现在什么字符之间
			nowletter = select.nextInt(90);
			int j = 1;
			if ((nowletter > 49 && nowletter < 58) || nowletter > 65) {
				if (nowletter > 49 && nowletter < 58) {
					j++;
				}
				if (j == 4)
					continue out;
				nowlet = (char) nowletter;
				target.append(nowlet);
				i++;
			} else {
				continue;
			}
		}
		return target.toString();
	}
}
