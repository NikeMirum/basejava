package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    private final ConnectionFactory cf;

    public SqlUtil(ConnectionFactory cf) {
        this.cf = cf;
    }

    public <T> T execute(String sqlStatement, SqlExecutor<T> executor) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw SqlExceptionUtil.convertException(e);
        }
    }

    public void execute(String sqlStatement) {
        execute(sqlStatement, PreparedStatement::execute);
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }
}