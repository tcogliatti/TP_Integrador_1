package csv;

import interfaces.InterfaceClienteDAO;
import interfaces.InterfaceDAO;
import interfaces.InterfaceProductoDAO;
import dto.Cliente;
import dto.Factura;
import dto.FacturaProducto;
import dto.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;

public class CSVcharger<T> {

    private String dataSetName;
    private String path = "./src/main/java/csv/";

    public CSVcharger() {

    }

    public void cargaClientes(InterfaceClienteDAO<Cliente> daoCliente) {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "clientes.csv"));
            for (CSVRecord row : parser) {
                daoCliente.registrar(new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarProductos(InterfaceProductoDAO<Producto> daoProducto) {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "productos.csv"));
            for (CSVRecord row : parser) {
                daoProducto.registrar(new Producto(Integer.parseInt(row.get("idProducto")), Float.parseFloat(row.get("valor")),  row.get("nombre")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarFacturas(InterfaceDAO<Factura> daoFactura) {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "facturas.csv"));
            for (CSVRecord row : parser) {
                daoFactura.registrar(new Factura(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")) ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void cargarFacturasProductos(InterfaceDAO<FacturaProducto> daoFacturaProducto) {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "facturas-productos.csv"));
            for (CSVRecord row : parser) {
                daoFacturaProducto.registrar(new FacturaProducto(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad"))));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
