package com.mini.core.jdbc;

import com.mini.core.data.builder.NamedSql;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface MiniJdbcNamedRepository extends MiniJdbcBaseRepository {
    /**
     * 执行SQL
     *
     * @param sql    SQL
     * @param params 参数
     * @return 执行结果 - 影响条数
     */
    int execute(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 执行SQL
     *
     * @param sql SQL和参数
     * @return 执行结果 - 影响条数
     */
    int execute(@NotNull NamedSql<?> sql);

    /**
     * 指执行SQL
     *
     * @param sql        SQL
     * @param paramsList 数据
     * @return 执行结果 - 影响条数
     */
    @NotNull
    int[] executeBatch(@NotNull String sql, Map<String, Object>[] paramsList);

    /**
     * 查询列表
     *
     * @param sql    SQL
     * @param params 参数
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(@NotNull String sql, @Nullable Map<String, Object> params, RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param sql    SQL
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(@NotNull NamedSql<?> sql, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param sql    SQL
     * @param params 参数
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(@NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param sql  SQL
     * @param type 类型类对象
     * @param <T>  解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(@NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询列表
     *
     * @param sql SQL
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(@NotNull NamedSql<?> sql);

    /**
     * 查询列表
     *
     * @param sql    SQL
     * @param type   类型类对象
     * @param params 参数
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(@NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param sql  SQL
     * @param type 类型类对象
     * @param <T>  解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(@NotNull NamedSql<?> sql, Class<T> type);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(long offset, int limit, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(long offset, int limit, @NotNull NamedSql<?> sql, RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(long offset, int limit, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(long offset, int limit, @NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(long offset, int limit, @NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(long offset, int limit, @NotNull NamedSql<?> sql);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(long offset, int limit, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param offset 跳过的数据条数
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(long offset, int limit, @NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(int limit, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(int limit, @NotNull NamedSql<?> sql, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(int limit, String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param limit 获取指定的条数
     * @param sql   SQL
     * @param type  类型类对象
     * @param <T>   解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryList(int limit, @NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(int limit, @NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询列表
     *
     * @param limit 获取指定的条数
     * @param sql   SQL
     * @return 查询结果
     */
    @NotNull
    List<Map<String, Object>> queryListMap(int limit, @NotNull NamedSql<?> sql);


    /**
     * 查询列表
     *
     * @param limit  获取指定的条数
     * @param sql    SQL
     * @param params 参数
     * @param type   类型类对象
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(int limit, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param limit 获取指定的条数
     * @param sql   SQL
     * @param type  类型类对象
     * @param <T>   解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> List<T> queryListSingle(int limit, @NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询对象
     *
     * @param sql    SQL
     * @param params 参数
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObject(@NotNull String sql, @Nullable Map<String, Object> params, @NotNull RowMapper<T> mapper);

    /**
     * 查询对象
     *
     * @param sql    SQL
     * @param mapper 映射器
     * @param <T>    解析器类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObject(@NotNull NamedSql<?> sql, @NotNull RowMapper<T> mapper);

    /**
     * 查询对象
     *
     * @param sql    SQL
     * @param params 参数
     * @param type   值的类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObject(@NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询对象
     *
     * @param sql  SQL
     * @param type 值的类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObject(@NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Map<String, Object> queryObjectMap(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Map<String, Object> queryObjectMap(@NotNull NamedSql<?> sql);

    /**
     * 查询对象
     *
     * @param sql    SQL
     * @param params 参数
     * @param type   值的类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObjectSingle(@NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询对象
     *
     * @param sql  SQL
     * @param type 值的类型
     * @return 查询结果
     */
    @Nullable
    <T> T queryObjectSingle(@NotNull NamedSql<?> sql, @NotNull Class<T> type);

    /**
     * 查询 String 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    String queryString(String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 String 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    String queryString(@NotNull NamedSql<?> sql);

    /**
     * 查询 Long 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Long queryLong(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Long 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Long queryLong(@NotNull NamedSql<?> sql);

    /**
     * 查询 Integer 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Integer queryInt(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Integer 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Integer queryInt(@NotNull NamedSql<?> sql);

    /**
     * 查询 Short 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Short queryShort(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Short 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Short queryShort(@NotNull NamedSql<?> sql);

    /**
     * 查询 Byte 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Byte queryByte(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Byte 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Byte queryByte(@NotNull NamedSql<?> sql);

    /**
     * 查询 Double 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Double queryDouble(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Double 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Double queryDouble(@NotNull NamedSql<?> sql);

    /**
     * 查询 Float 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Float queryFloat(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Float 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Float queryFloat(@NotNull NamedSql<?> sql);

    /**
     * 查询 Boolean 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Boolean queryBoolean(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Boolean 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Boolean queryBoolean(@NotNull NamedSql<?> sql);

    /**
     * 查询 Timestamp 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Timestamp queryTimestamp(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Timestamp 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Timestamp queryTimestamp(@NotNull NamedSql<?> sql);

    /**
     * 查询 Date 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Date queryDate(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Date 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Date queryDate(@NotNull NamedSql<?> sql);

    /**
     * 查询 Time 对象
     *
     * @param sql    SQL
     * @param params 参数
     * @return 查询结果
     */
    @Nullable
    Time queryTime(@NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询 Time 对象
     *
     * @param sql SQL
     * @return 查询结果
     */
    @Nullable
    Time queryTime(@NotNull NamedSql<?> sql);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param params   参数
     * @param mapper   映射器
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPage(@NotNull Pageable pageable, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param mapper   映射器
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPage(@NotNull Pageable pageable, @NotNull NamedSql<?> sql, @NotNull RowMapper<T> mapper);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param params   参数
     * @param type     类型类对象
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPage(@NotNull Pageable pageable, String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param type     类型类对象
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPage(@NotNull Pageable pageable, @NotNull NamedSql<?> sql, Class<T> type);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param params   参数
     * @return 查询结果
     */
    @NotNull
    Page<Map<String, Object>> queryPageMap(@NotNull Pageable pageable, @NotNull String sql, @Nullable Map<String, Object> params);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @return 查询结果
     */
    @NotNull
    Page<Map<String, Object>> queryPageMap(@NotNull Pageable pageable, @NotNull NamedSql<?> sql);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param params   参数
     * @param type     类型类对象
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPageSingle(@NotNull Pageable pageable, @NotNull String sql, @Nullable Map<String, Object> params, @NotNull Class<T> type);

    /**
     * 查询列表
     *
     * @param pageable 分页数据
     * @param sql      SQL
     * @param type     类型类对象
     * @param <T>      解析器类型
     * @return 查询结果
     */
    @NotNull
    <T> Page<T> queryPageSingle(@NotNull Pageable pageable, @NotNull NamedSql<?> sql, @NotNull Class<T> type);
}