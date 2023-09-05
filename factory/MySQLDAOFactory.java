package factory;

import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory  extends DAOFactory{

    //JDBC driver y base de datos URL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_practico1";
    //base de datos credenciales
    private static final String USER = "root";
    private static final String PASS = "";

    private static MySQLDAOFactory instancia;
    protected static Connection conexion;

    //Constructor privado para evitar crear un new una nueva instancia
    protected MySQLDAOFactory(){};

    public static MySQLDAOFactory getInstancia(){
        if(instancia == null){
            instancia = new MySQLDAOFactory();
        }
        return instancia;
    }

    public static Connection conectar() throws  Exception{
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (SQLException e) {
            throw e;
        }
        return conexion;
    }

    public InterfaceDAO getClienteDAO() throws Exception {
        return new ClienteDAO();
    };
    public FacturaDAO getFacturaDAO(){
        return null;
    };
    public ProductoDAO getProductoDAO(){
        return null;
    };
    public FacturaProductoDAO getFacturaProductoDAO(){
        return null;
    };
}
