package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ProductoFac interactua con la tabla de productos en la base de datos.
 * @author Kevin Santiago
 *
 */
public class ProductoFac {
    private Connection conexion;

    /**
     * Constructor de la clase ProductoFac.
     * Inicializa la conexión a la base de datos. 
     * @param conexion La conexión a la base de datos.
     */
    public ProductoFac(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Este metodo recupera la lista de productos de la base de datos.
     * @return Una lista con los productos guardados en la base de datos.
     * @throws SQLException
     */
    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String consulta = "SELECT * FROM producto";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
             ResultSet resultado = declaracion.executeQuery()) {
            while (resultado.next()) {
                Producto producto = new Producto(
                        resultado.getString("referencia"),
                        resultado.getString("descripcion"),
                        resultado.getString("categoria_id"),
                        resultado.getString("stock"),
                        resultado.getString("valor")
                );
                productos.add(producto);
            }
        }

        return productos;
    }

    /**
     * Este metodo busca un producto de la base de datos según su referencia.
     * @param referencia La referencia del producto que se desea buscar.
     * @return	El producto al que le corresponde la referencia proporcionada.
     * @throws SQLException
     */
    public Producto buscarProductoPorReferencia(String referencia) throws SQLException {
        if (referencia == null) {
            throw new IllegalArgumentException("La referencia no puede ser nula.");
        }
        Producto producto = null;
        String consulta = "SELECT * FROM producto WHERE referencia = ?";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
            declaracion.setString(1, referencia);
            try (ResultSet resultado = declaracion.executeQuery()) {
                if (resultado.next()) {
                    producto = new Producto(
                            resultado.getString("referencia"),
                            resultado.getString("descripcion"),
                            resultado.getString("categoria_id"),
                            resultado.getString("stock"),
                            resultado.getString("valor")
                    );
                } else {
                    System.out.println("No se encontró ningún producto con la referencia proporcionada.");
                }
            }
        }

        return producto;
    }
}