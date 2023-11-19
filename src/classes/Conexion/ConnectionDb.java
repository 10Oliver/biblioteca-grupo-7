package classes.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDb{

    public static void closeConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void close(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Connection connection;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "F!re17";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            System.out.println("Connection successful.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include the library in your project.");
        } catch (SQLException e) {
            System.err.println("Connection failed. Error message: " + e.getMessage());
        }
        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
}