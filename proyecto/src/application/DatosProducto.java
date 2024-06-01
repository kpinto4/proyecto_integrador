package application;

/**
 * Importaciones necesarias para el desarrollo.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert;

/**
 * - La clase DatosProducto sirve para manejar los procedimientos correspondientes en la base de datos.
 * @author Kevin Santiago
 *
 */
public class DatosProducto {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "proto";
    private static final String PASSWORD = "proto";
/*
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";
*/

    /**
     * - Obtiene todos los datos de productos desde la base de datos.
     * @return Una lista de productos existentes en la base de datos.
     */
    public LinkedList<Producto> getDatos() {
        LinkedList<Producto> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM PRODUCTO");
             ResultSet result = st.executeQuery()) {

            while (result.next()) {
                Producto producto = new Producto(
                        result.getString("referencia"),
                        result.getString("descripcion"),
                        result.getString("categoria_id"),
                        result.getString("stock"),
                        result.getString("valor")
                );
                data.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * - Guarda un nuevo producto en la base de datos.
     * @param producto el producto a guardar.
     */
    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO PRODUCTO (referencia, descripcion, categoria_id, stock, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getReferencia());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setString(3, producto.getCategoria_id());
            pstmt.setString(4, producto.getStock());
            pstmt.setString(5, producto.getValor());

            pstmt.executeUpdate();
            System.out.println("Producto guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el producto en la base de datos: " + e.getMessage());
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Datos Incorrectos");
            error.setContentText("Por favor, verifique que ID sea numerico y CATEGORIA exista.");
            error.setTitle("ERROR AL GUARDAR EL PRODUCTO");
            error.show();
        }
    
    }

    /**
     * - Elimina un producto de la base de datos por su referencia.
     * @param referencia es la referencia del producto a eliminar.
     */
    public void eliminarProducto(String referencia) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM PRODUCTO WHERE referencia = ?")) {

            st.setString(1, referencia);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * - Actualiza un producto en la base de datos.
     * @param producto es el producto actualizado.
     */
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE PRODUCTO SET descripcion = ?, categoria_id = ?, stock = ?, valor = ? WHERE referencia = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getCategoria_id());
            pstmt.setString(3, producto.getStock());
            pstmt.setString(4, producto.getValor());
            pstmt.setString(5, producto.getReferencia());
            

            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto en la base de datos: " + e.getMessage());
        }
    }
    
    /**
     * - Verifica si un producto existe en la base de datos por su referencia.
     * @param referencia es la referencia del producto a verificar.
     * @return true si el producto existe, false de lo contrario.
     */
    public boolean existeProducto(String referencia) {
        String sql = "SELECT COUNT(*) FROM PRODUCTO WHERE REFERENCIA = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, referencia);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}