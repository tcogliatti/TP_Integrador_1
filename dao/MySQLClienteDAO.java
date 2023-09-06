package dao;

import dto.Cliente;
import factory.MySQLDAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLClienteDAO implements InterfaceDAO<Cliente> {
    public MySQLClienteDAO() throws Exception {
        if(!MySQLDAOFactory.checkIfExistsEntity("cliente", MySQLDAOFactory.conectar()))
            this.crearTabla();
    }

    @Override
    public Cliente buscar(int id) throws Exception {
        Connection conexion = MySQLDAOFactory.conectar();
        try {
            String query = "SELECT * FROM cliente " +
                    "WHERE idCliente = ?";
            PreparedStatement st = conexion.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
            else
                return null;
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        Connection conexion = MySQLDAOFactory.conectar();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "DELETE FROM cliente " +
                            "WHERE idCliente =? ");
            st.setInt(1, cliente.getIdCliente());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void registrar(Cliente cliente) throws Exception {
        Connection conexion = MySQLDAOFactory.conectar();
        try {
            PreparedStatement st = conexion.prepareStatement(
                    "INSERT INTO cliente (nombre, email) " +
                            "VALUES (?,?) ");
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getEmail());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void modificar(Cliente cliente) throws Exception {
        Connection conexion = MySQLDAOFactory.conectar();
        try {
            String query =
                    "UPDATE  cliente " +
                    "SET nombre = ?, email = ?" +
                    "WHERE idCliente = ?";
            PreparedStatement st = conexion.prepareStatement(query);
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getEmail());
            st.setInt(3, cliente.getIdCliente());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    public void crearTabla() throws Exception {
        Connection conexion = MySQLDAOFactory.conectar();
        String queryTablaCliente = "CREATE TABLE IF NOT EXISTS cliente" +
                "(idCliente INT AUTO_INCREMENT, " +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY (idCliente))";
        conexion.prepareStatement(queryTablaCliente).execute();
        conexion.close();
        System.out.println("Tabla Cliente Creada");
    }
}
