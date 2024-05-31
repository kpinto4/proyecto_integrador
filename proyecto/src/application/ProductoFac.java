package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoFac {
    private Connection conexion;

    public ProductoFac(Connection conexion) {
        this.conexion = conexion;
    }

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