package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {

    public static <T> T execute(ConnectionFactory cf, String sqlStatement, SqlExecutor<T> executor) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw SqlExceptionUtil.convertException(e);
        }
    }

    public static void execute(ConnectionFactory cf, String sqlStatement) {
        execute(cf, sqlStatement, PreparedStatement::execute);
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }
}