package com.haike.web.entity.response;

/**
 * @author Dana
 *	网络请求返回状态枚举类
 */
public enum Status {

	/**
	 * OK 成功
	 */
	OK(200, "成功"),
	/**
	 * ACCEPTED 已经接受请求
	 */
	ACCEPTED(202, "已经接受请求"),
	/**
	 * BAD_REQUEST 缺少必要的请求参数
	 */
	BAD_REQUEST(400, "缺少必要的请求参数"),
	/**
	 * BAD_REQUEST_PARAMS 参数错误
	 */
	BAD_REQUEST_PARAMS(401, "参数错误"),
	/**
	 * NOT_FOUND 没有找到对应请求
	 */
	NOT_FOUND(404, "没有找到对应请求"),
	/**
	 * INTERNAL_SERVER_ERROR 服务器出错
	 */
	INTERNAL_SERVER_ERROR(500, "服务器出错");
	
	private int code;// 状态码
	private String message;// 消息

	private Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取枚举
	 * 
	 * @param code
	 * @return
	 */
	public static Status getStatusEnum(int code) {
		for (Status s : Status.values()) {
			if (s.getCode() == code) {
				return s;
			}
		}
		return null;
	}
	
}
