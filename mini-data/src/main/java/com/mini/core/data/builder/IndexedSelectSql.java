package com.mini.core.data.builder;

import com.mini.core.data.builder.statement.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@SuppressWarnings("UnusedReturnValue")
public final class IndexedSelectSql extends IndexedSql<IndexedSelectSql> implements BaseSelectSql<IndexedSelectSql> {
    private final SelectSql<IndexedSelectSql> impl = new SelectSql<>() {
        protected IndexedSelectSql getSelectSql() {
            return IndexedSelectSql.this;
        }
    };

    private IndexedSelectSql() {
    }

    public IndexedSelectSql SELECT(String... column) {
        return impl.SELECT(column);
    }

    public final <T> IndexedSelectSql SELECT(@NotNull Class<T> type) {
        return impl.SELECT(type);
    }

    public IndexedSelectSql SELECT(Consumer<SelectStatement> consumer) {
        return impl.SELECT(consumer);
    }

    public IndexedSelectSql FROM(String... tables) {
        return impl.FROM(tables);
    }

    public final <T> IndexedSelectSql FROM(@NotNull Class<T> type) {
        return impl.FROM(type);
    }

    public <E> IndexedSelectSql SELECT_FROM(@NotNull Class<E> type) {
        return impl.SELECT_FROM(type);
    }

    public IndexedSelectSql JOIN(String join) {
        return impl.JOIN(join);
    }

    public IndexedSelectSql INNER_JOIN(String join) {
        return impl.INNER_JOIN(join);
    }

    public IndexedSelectSql LEFT_JOIN(String join) {
        return impl.LEFT_JOIN(join);
    }

    public IndexedSelectSql RIGHT_JOIN(String join) {
        return impl.RIGHT_JOIN(join);
    }

    public IndexedSelectSql LEFT_OUTER_JOIN(String join) {
        return impl.LEFT_OUTER_JOIN(join);
    }

    public IndexedSelectSql RIGHT_OUTER_JOIN(String join) {
        return impl.RIGHT_OUTER_JOIN(join);
    }

    public IndexedSelectSql CROSS_JOIN(String join) {
        return impl.CROSS_JOIN(join);
    }

    public IndexedSelectSql JOIN(Consumer<JoinStatement> consumer) {
        return impl.JOIN(consumer);
    }

    public IndexedSelectSql WHERE(String where) {
        return impl.WHERE(where);
    }

    public IndexedSelectSql WHERE(Consumer<WhereStatement> consumer) {
        return impl.WHERE(consumer);
    }

    public IndexedSelectSql GROUP_BY(String... columns) {
        return impl.GROUP_BY(columns);
    }

    public IndexedSelectSql HAVING(Consumer<HavingStatement> consumer) {
        return impl.HAVING(consumer);
    }

    public IndexedSelectSql ORDER_BY(String... columns) {
        return impl.ORDER_BY(columns);
    }

    public IndexedSelectSql ORDER_BY_ASC(String... columns) {
        return impl.ORDER_BY_ASC(columns);
    }

    public IndexedSelectSql ORDER_BY_DESC(String... columns) {
        return impl.ORDER_BY_DESC(columns);
    }

    public IndexedSelectSql ORDER_BY(Consumer<OrderByStatement> consumer) {
        return impl.ORDER_BY(consumer);
    }

    @Override
    public final String getSql() {
        return impl.getSql();
    }

    public static IndexedSelectSql of(Consumer<IndexedSelectSql> consumer) {
        IndexedSelectSql builder = new IndexedSelectSql();
        consumer.accept(builder);
        return builder;
    }

    public static IndexedSelectSql of() {
        return new IndexedSelectSql();
    }
}