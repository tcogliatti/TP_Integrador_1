package factory;

import dao.*;

import java.sql.*;
import java.util.Locale;

public class MySQLDAOFactory extends DAOFactory {

    //JDBC driver y base de datos URL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_practico1";

    //base de datos credenciales
    private static final String USER = "root";
    private static final String PASS = "";

    private static MySQLDAOFactory instancia = new MySQLDAOFactory();
    protected static Connection conexion;

    //Constructor privado para evitar crear un new una nueva instancia
    protected MySQLDAOFactory() {
        Locale.setDefault(new Locale("en", "US"));
    }

    public static MySQLDAOFactory getInstancia() {
        return instancia;
    }

    public static Connection conectar() throws Exception {
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (SQLException e) {
            throw e;
        }
        return conexion;
    }

    public static boolean checkIfExistsEntity(String table, Connection conn) throws SQLException {
        try {
            conn = MySQLDAOFactory.conectar();
            String query = "SELECT * " +
                    "FROM information_schema.tables " +
                    "WHERE table_schema = 'db_practico1' " +
                    "AND table_name = '" + table + "'";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public InterfaceDAO getClienteDAO() throws Exception {
        return new MySQLClienteDAO();
    }

    public InterfaceDAO getFacturaDAO() throws Exception {
        return new MySQLFacturaDAO();
    }

    public MySQLProductoDAO getProductoDAO() throws Exception {
        return new MySQLProductoDAO();
    }

    public MySQLFacturaProductoDAO getFacturaProductoDAO() throws Exception {
        return new MySQLFacturaProductoDAO();
    }
}
