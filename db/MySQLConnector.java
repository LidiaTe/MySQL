package db;

import settings.ISettings;
import settings.PropertyReader;

import java.sql.*;
import java.util.Map;
//import java.util.Properties;

public class MySQLConnector implements IDBConnector {
    private static Connection connection = null;
    private static Statement statement = null;

    public MySQLConnector() {
        connect();
    }

    private void connect() {
        ISettings reader = new PropertyReader();
        Map<String, String> settings = reader.read();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(settings.get("url") + "/" + settings.get("db_name"),
                        settings.get("username"),
                        settings.get("password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeRequest(String response){
        try {
            statement.execute(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet executeRequestWithAnswer(String response) {
        try {
            return statement.executeQuery(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet executeUpdate(String response){
        try {
            statement.executeUpdate(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}