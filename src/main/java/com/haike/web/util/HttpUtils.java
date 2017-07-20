package com.haike.web.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
}