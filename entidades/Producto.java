package entidades;

public class Producto {

    private int idProducto;
    private float valor;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public float getValor() {
        return valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
