package factory;

import dao.*;

public abstract class DAOFactory {

    public static final int MYSQL_JDBC = 1;

    public static DAOFactory getDAOFactory(int DB_Factory) {
        switch (DB_Factory) {
            case MYSQL_JDBC:
                return MySQLDAOFactory.getInstancia();
            default:
                return null;
        }
    }

    public abstract InterfaceDAO getClienteDAO() throws Exception;

    public abstract InterfaceDAO getFacturaDAO() throws Exception;

    public abstract InterfaceDAO getProductoDAO() throws Exception;

    public abstract InterfaceDAO getFacturaProductoDAO() throws Exception;


}
