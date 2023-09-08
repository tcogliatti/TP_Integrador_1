import dao.*;
import factory.*;
public class app {

    private String nombre;
    public static void main(String[] args) throws Exception {

        DAOFactory MySQL_Factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
        InterfaceDAO clienteDao = MySQL_Factory.getClienteDAO();
        InterfaceDAO productoDao = MySQL_Factory.getProductoDAO();
        InterfaceDAO facturaDao = MySQL_Factory.getFacturaDAO();
        InterfaceDAO facturaProductoDao = MySQL_Factory.getFacturaProductoDAO();

    }


}
