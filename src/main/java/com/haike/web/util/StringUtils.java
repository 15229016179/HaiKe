/**
 * @Title: StringUtils.java
 * @Package com.warmdoctor.ows.common.util
 * @author bruce
 * @date 2015年4月27日 上午10:32:40
 * @version V1.0
 */
package com.haike.web.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 字符串操作工具类
 *
 * @author bruce
 * @ClassName: StringUtils
 * @date 2015年4月27日 上午10:32:40
 */
public class StringUtils {

    /**
     * @Fields LOG : 日志组件
     */
    private static final Log LOG = LogFactory.getLog(StringUtils.class);

    public static final String HTTP_MATCH = "^(https|http|ftp):\\/\\/.*";

    /**
     * 判断是否为空参数
     *
     * @param str
     * @return boolean
     * @author :bruce
     * @create 2015年4月17日下午5:35:23
     */
    public static final boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equalsIgnoreCase(str);
    }

    /**
     * 判断是否为httpurl的格式
     *
     * @param url
     * @return boolean
     * @author :bruce
     * @create 2015年4月27日下午2:36:13
     */
    public static final boolean isHttp(String url) {
        return !isEmpty(url) && url.matches(HTTP_MATCH);
    }

    /**
     * 字符串转换首字母小写
     *
     * @param str
     * @return String
     * @author :bruce
     * @create 2015年5月13日上午2:10:53
     */
    public static final String firstLowercase(String str) {
        String rs = str;
        char[] chars = new char[1];
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            rs = str.replaceFirst(temp, temp.toLowerCase());
        }
        return rs;
    }

    /**
     * 字符串转换首字母大写
     *
     * @param str
     * @return
     */
    public static final String firstUpperCase(String str) {
        String rs = str;
        char[] chars = new char[1];
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            rs = str.replaceFirst(temp, temp.toUpperCase());
        }
        return rs;
    }

    /**
     * 字符串输出
     *
     * @param obj
     * @return String
     * @author :bruce
     * @create 2015年4月29日下午1:46:56
     */
    public static final String toString(Object obj) {
        if (!ObjectUtils.isEmpty(obj)) {
            if (obj instanceof List) {
                return toList((List<?>) obj);
            }
            return obj.toString();
        }
        return null;
    }

    /**
     * 获取List的字符串信息
     *
     * @param list
     * @return String
     * @author :bruce
     * @create 2015年4月29日下午1:48:56
     */
    protected static final String toList(List<?> list) {
        StringBuffer sb = new StringBuffer();
        for (Object obj : list) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    /**
     * 获取拼接的字段信息
     *
     * @param obj
     * @return String
     * @author :bruce
     * @create 2015年5月12日下午6:30:32
     */
    public static final String joinString(Object obj) {
        List<String> ns = new ArrayList<String>();
        Map<String, Object> temp = new HashMap<String, Object>();
        Method[] ms = obj.getClass().getMethods();
        for (Method method : ms) {
            String mName = method.getName();
            if (mName.startsWith("get")) {
                String element = mName.substring(3, mName.length());
                try {
                    Class<?> rType = method.getReturnType();
                    if (!ClassUtils.isClass(rType)) {
                        Object parm = method.invoke(obj);
                        if (!ObjectUtils.isEmpty(parm)) {
                            element = StringUtils.firstLowercase(element);
                            if (element.contains("package")) {
                                element = "package";
                            }
                            ns.add(element);
                            temp.put(element, parm);
                        }
                    }
                } catch (Exception e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
        // 排序
        Collections.sort(ns);
        StringBuffer sb = new StringBuffer();
        for (String key : ns) { // 点击是否需要key
            if (temp.containsKey(key)) {
                sb.append("&").append(key).append("=").append(temp.get(key));
            }
        }
        String sgin = sb.substring(1, sb.toString().length());
        return sgin;
    }

    /**
     * 隐藏手机号
     *
     * @param phone
     * @return
     */
    public static final String hiddenPhone(String phone) {
        if (!StringUtils.isEmpty(phone) && phone.length() > 10) {
            String s = "";
            //隐藏中间4位数
            char[] chr = phone.toCharArray();
            for (int i = 0; i < phone.length(); i++) {
                if (i >= 4 && i <= 7) {
                    s += '*';
                } else {
                    s += chr[i];
                }
            }
            return String.valueOf(s);
        }
        return phone;
    }

    /**
     * 字体隐藏
     *
     * @param patientName
     * @param i
     * @return
     */
    public static final String hiddenLenght(String patientName, int i) {
        if (!StringUtils.isEmpty(patientName) && patientName.length() > i) {
            patientName = patientName.substring(0, i);
        }
        return patientName;
    }
}
