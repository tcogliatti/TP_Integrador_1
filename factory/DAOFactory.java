package factory;

import dao.*;

public abstract class DAOFactory {

    public static final int MYSQL_JDBC = 1;

    public static DAOFactory getDAOFactory(int DB_Factory) {
        switch (DB_Factory) {
            case MYSQL_JDBC:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }

    public abstract InterfaceDAO getClienteDAO() throws Exception;

    public abstract FacturaDAO getFacturaDAO() throws Exception;

    public abstract ProductoDAO getProductoDAO() throws Exception;

    public abstract FacturaProductoDAO getFacturaProductoDAO() throws Exception;


}
