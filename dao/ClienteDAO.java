package dao;

import dto.Cliente;
import factory.MySQLDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ClienteDAO implements InterfaceDAO<Cliente> {

    private Connection conexion;

    public ClienteDAO() throws Exception{
        this.crearTabla();
    }

    @Override
    public Cliente buscar(int id) throws Exception {
        try{
            MySQLDAOFactory.conectar();
            String query = "SELECT * FROM cliente " +
                            "WHERE id = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
            else
                return null;
        }catch (Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        try{
            this.conexion = MySQLDAOFactory.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM cliente " +
                            "WHERE id =? ");
            st.setInt(1, cliente.getIdCliente());
            st.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    @Override
    public void registrar(Cliente cliente) throws Exception {
        try{
            this.conexion = MySQLDAOFactory.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO cliente (nombre, email) " +
                        "VALUES (?,?) ");
            st.setString(1,cliente.getNombre());
            st.setString(2, cliente.getEmail() );
            st.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    @Override
    public void modificar(Cliente cliente) throws Exception {
        try{
            this.conexion = MySQLDAOFactory.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE  cliente" +
                            "nombre=?, email=?" +
                            "WHERE  id = ? ");
            st.setString(1,cliente.getNombre());
            st.setString(2, cliente.getEmail());
            st.setInt(3,cliente.getIdCliente());
            st.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            this.desconectar();
        }
    }

    public void crearTabla() throws Exception {
        this.conexion = MySQLDAOFactory.conectar();
        String queryTablaCliente = "CREATE TABLE IF NOT EXISTS cliente" +
                "(idCliente INT, " +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY (idCliente))";
        this.conexion.prepareStatement(queryTablaCliente).execute();
        this.conexion.close();
    }


    public void desconectar() throws SQLException {
        if(conexion != null){
            if(!conexion.isClosed()){
                conexion.close();
            }
        }
    }
}
