package com.mini.core.web.argument.defaults;

import com.mini.core.util.reflect.MiniParameter;
import com.mini.core.web.annotation.Param;
import com.mini.core.web.argument.ArgumentResolver;
import com.mini.core.web.interceptor.ActionInvocation;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Named
@Singleton
public final class ArgumentResolverPartDefault implements ArgumentResolver {
	
	@Override
	public boolean supportParameter(MiniParameter parameter) {
		if (nonNull(parameter.getAnnotation(Param.class))) {
			return false;
		}
		return Part.class == parameter.getType();
	}
	
	/**
	 * 获取参数名称
	 * @param parameter 参数对象
	 * @return 参数名称
	 */
	@Nonnull
	private String getParameterName(MiniParameter parameter) {
		return parameter.getName();
	}
	
	@Override
	public Object getValue(MiniParameter parameter, ActionInvocation invocation) {
		try {
			String name = this.getParameterName(parameter);
			return ofNullable(invocation.getRequest().getPart(name))
					.filter(part -> part.getSize() > 0)
					.orElse(null);
		} catch (IOException | ServletException ignored) {
		}
		return null;
	}
}
