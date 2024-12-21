package ma.ensa.ordcusthreads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    String url = "jdbc:mysql://localhost:3306/inventory";
    String name = "root";
    String passwd = "root";

    private Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, name, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
