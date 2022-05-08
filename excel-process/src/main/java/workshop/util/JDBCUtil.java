package workshop.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JDBCUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            final String url = "jdbc:oracle:thin:@localhost:1521:XE";
            final String username = "ora";
            final String password = "root";
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                log.error("ERROR:", e);
            }
        }
        return connection;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null || statement != null || resultSet != null) {
                resultSet.close();
                assert statement != null;
                statement.close();
                assert connection != null;
                connection.close();
            }
        } catch (SQLException e) {
            log.error("ERROR:", e);
        }
    }
}
