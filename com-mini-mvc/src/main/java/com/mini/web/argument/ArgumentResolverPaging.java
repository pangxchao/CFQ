package com.mini.web.argument;

import com.mini.jdbc.util.Paging;
import com.mini.util.TypeUtil;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@Singleton
public final class ArgumentResolverPaging implements ArgumentResolver {
    @Override
    public Object value(@Nonnull String name, @Nonnull Class<?> type, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) {
        String page = request.getParameter("page"), limit = request.getParameter("limit");
        return new Paging(TypeUtil.castToIntVal(page), TypeUtil.castToIntVal(limit));
    }

}