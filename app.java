import dao.InterfaceDAO;
import factory.DAOFactory;

public class app {

    //private static MySQLClienteDAO clienteDAO;

    public static void main(String[] args) throws Exception {

        DAOFactory MySQL_Factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
        InterfaceDAO clienteDao = MySQL_Factory.getClienteDAO();
        
        //clienteDao.registrar("daira", "admin@gmail.com");
    }
}
