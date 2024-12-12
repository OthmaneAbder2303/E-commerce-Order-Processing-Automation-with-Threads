package ma.ensa.ordcusthreads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    String databaseUrl = "jdbc:mysql://localhost:3306/inventory";
    String username = "root";
    String userPassword = "root";

    private Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(databaseUrl, username, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
