package db;

import java.sql.ResultSet;

public interface IDBConnector {
    ResultSet executeRequest(String response);
    ResultSet executeRequestWithAnswer(String response);
    void close();

    ResultSet executeUpdate(String sqlRequest);
}
