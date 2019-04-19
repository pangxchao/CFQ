package com.mini.util.dao.row;

import com.mini.util.dao.IRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class LongRow implements IRow<Long> {
    public static final LongRow INSTANCE = new LongRow();

    private LongRow() {}

    @Override
    public Long execute(ResultSet rs, int index) throws SQLException {
        return rs.getLong(index);
    }
}
