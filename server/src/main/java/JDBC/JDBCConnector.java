package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    public JDBCConnector() {
    }

    public static Connection GetConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sklad?useUnicode=true&characterEncoding=UTF-8", "root", "Dhym490");
        } catch (ClassNotFoundException | SQLException var1) {
            throw new RuntimeException(var1);
        }
    }
}