/**
 * @Title: ObjectUtils.java
 * @Package com.warmdoctor.ows.core.util
 * @author bruce
 * @date 2015年4月27日 下午3:38:28
 * @version V1.0
 */
package com.haike.web.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 对象工具类
 *
 * @author bruce
 * @ClassName: ObjectUtils
 * @date 2015年4月27日 下午3:38:28
 */
public class ObjectUtils {

    /**
     * 判断对象是否为null
     *
     * @param obj
     * @return boolean
     * @author :bruce
     * @create 2015年4月27日下午3:39:23
     */
    public static final boolean isEmpty(Object obj) {
        if (obj instanceof List) {
            return obj == null || ((List<?>) obj).isEmpty();
        }
        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        }
        return obj == null;
    }

    /**
     * 消息转换
     *
     * @param str
     * @param cls
     * @return T
     * @author :bruce
     * @create 2015年4月30日下午10:08:22
     */
    @SuppressWarnings("unchecked")
    public static final <T> T format(String str, Class<T> cls) {
        if (cls.equals(String.class)) { // 判断消息是否属于String
            return (T) str;
        }
        if (cls.equals(Integer.class) || cls.equals(int.class)) {
            return (T) new Integer(str);
        }
        if (cls.equals(Long.class) || cls.equals(long.class)) {
            return (T) new Long(str);
        }
        if (cls.equals(Double.class) || cls.equals(double.class)) {
            return (T) new Double(str);
        }
        if (cls.equals(Float.class) || cls.equals(float.class)) {
            return (T) new Float(str);
        }
        if (cls.equals(Boolean.class) || cls.equals(boolean.class)) {
            return (T) new Boolean(str);
        }
        return null;
    }

    /**
     * 多参数复制操作
     *
     * @param source
     * @param arg
     * @param pars
     * @return
     */
    public static final Object setting(Object source, Object arg, String... pars) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!ObjectUtils.isEmpty(source) && !ObjectUtils.isEmpty(arg)) {//仅仅当对象不为null时有效
            for (String par : pars) {
                if (!StringUtils.isEmpty(par)) {
                    String methodName = "set" + StringUtils.firstUpperCase(par);
                    Method method = source.getClass().getMethod(methodName, String.class);
                    if (!ObjectUtils.isEmpty(method)) {
                        method.invoke(source, arg);
                    }
                }
            }
        }
        return source;
    }

}
