package ua.goit.jdbc.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManagerConnector {

    private String url;
    private static Properties properties = new Properties();
    private static Connection connection;

    static {
        var paramsStream = DatabaseManagerConnector.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(paramsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        properties.setProperty("user", System.getenv("user"));
        properties.setProperty("password", System.getenv("password"));
        try {
            connection = DriverManager.getConnection(properties.getProperty("database.url"), properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }
}
