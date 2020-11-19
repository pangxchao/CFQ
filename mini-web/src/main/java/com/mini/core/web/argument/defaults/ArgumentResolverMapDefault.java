package com.mini.core.web.argument.defaults;

import com.mini.core.util.reflect.MiniParameter;
import com.mini.core.web.annotation.Param;
import com.mini.core.web.argument.ArgumentResolver;
import com.mini.core.web.interceptor.ActionInvocation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Named
@Singleton
public final class ArgumentResolverMapDefault implements ArgumentResolver {
	
	@Override
	public boolean supportParameter(MiniParameter parameter) {
		if (nonNull(parameter.getAnnotation(Param.class))) {
			return false;
		}
		if (Map.class != parameter.getType()) {
			return false;
		}
		Type type = parameter.getParameterizedType();
		if (type instanceof ParameterizedType) {
			var t = (ParameterizedType) type;
			var arr = t.getActualTypeArguments();
			if (arr == null || arr.length != 2) {
				return false;
			}
			if (!arr[0].getTypeName().equals(String.class.getName())) {
				return false;
			}
			return arr[1].getTypeName().equals(String.class.getName());
		}
		return false;
	}
	
	@Override
	public Object getValue(MiniParameter parameter, ActionInvocation invocation) {
		HashMap<String, String> result = new HashMap<>();
		invocation.getRequest().getParameterNames().asIterator().forEachRemaining(name -> {
			result.put(name, invocation.getRequest().getParameter(name)); //
		});
		for (Map.Entry<String, String> entry : invocation.getUriParameters().entrySet()) {
			result.putIfAbsent(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
