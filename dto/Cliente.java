package dto;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String email;

    public Cliente(Integer id, String nombre, String email){
        this.idCliente = id;
        this.email = email;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
