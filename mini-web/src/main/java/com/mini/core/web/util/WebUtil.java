package com.mini.core.web.util;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * WebUtil
 * @author XChao
 */
public final class WebUtil {
	/**
	 * 获取访问该项目的基础URL
	 * @param req request 对象
	 * @return 如：http://localhost:8080
	 */
	public static String getDomain(HttpServletRequest req) {
		final StringBuilder builder = new StringBuilder();
		builder.append(req.getScheme()).append("://");
		builder.append(req.getServerName());
		if (req.getServerPort() == 80) {
			return builder.toString();
		}
		// 处理端口
		builder.append(":").append(req.getServerPort());
		return builder.toString();
	}
	
	/**
	 * 获取访问当前项目请求的路径
	 * @param req request 对象
	 * @return 路径
	 */
	public static String getRequestPath(@Nonnull HttpServletRequest req) {
		if (isBlank(req.getContextPath())) return req.getRequestURI();
		return replace(req.getRequestURI(), req.getContextPath(), "", 1);
	}
	
	/**
	 * 根据 Request 获取当前请求的绝对路径
	 * @param request HttpServletRequest 对象
	 * @return 如：http://localhost:8080/user/login.htm
	 */
	public static String getAbsoluteUrl(HttpServletRequest request) {
		return getDomain(request) + request.getRequestURI();
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request 请求数据
	 * @return IP地址
	 */
	public static String getIpAddress(HttpServletRequest request) {
		final String ip = request.getRemoteAddr();
		return defaultIfBlank(ip, "127.0.0.1");
	}
}
