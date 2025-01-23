package data;

import java.sql.Connection;

public interface IDB {
    String createConnection();
    void closeConnection();
}
