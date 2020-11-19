package com.mini.core.web.annotation;

import com.mini.core.web.model.IModel;

import java.lang.annotation.*;

/**
 * 控制器方法注解
 * @author xchao
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
	
	/**
	 * 返回数据类型
	 * @return 数据类型
	 */
	Class<? extends IModel<?>> value();
	
	/**
	 * 访问地址
	 * @return 访问地址
	 */
	String[] url() default {};
	
	/**
	 * 视图路径
	 * @return 视图路径
	 */
	String path() default "";
	
	/**
	 * 默认访问路径的后缀
	 * @return 访问路径的后缀
	 */
	String suffix() default ".htm";
	
	/**
	 * 该方法支持的请求类型
	 * @return 请求类型数组
	 */
	Method[] method() default {
			Method.GET,
			Method.HEAD,
			Method.POST,
			Method.PUT,
			Method.DELETE,
			Method.OPTIONS,
			Method.TRACE
	};
	
	/**
	 * 扩展字段
	 * @return 扩展数据
	 */
	String[] extend() default {};
	
	/**
	 * 方法类型
	 * @author xchao
	 */
	enum Method {
		GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE
	}
}
