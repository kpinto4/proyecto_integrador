package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatosProducto {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";

    public LinkedList<Producto> getDatos() {
        LinkedList<Producto> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM PRODUCTO");
             ResultSet result = st.executeQuery()) {

            while (result.next()) {
                Producto producto = new Producto(
                        result.getString("codproducto"),
                        result.getString("descripcion"),
                        result.getString("categoria"),
                        result.getString("valor"),
                        result.getString("stock")
                );
                data.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO PRODUCTO (codproducto, descripcion, categoria, stock, valor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getCodProducto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setString(3, producto.getCategoria());
            pstmt.setString(4, producto.getValor());
            pstmt.setString(5, producto.getStock());

            pstmt.executeUpdate();
            System.out.println("Producto guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el producto en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarProducto(String codProducto) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM PRODUCTO WHERE codproducto = ?")) {

            st.setString(1, codProducto);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE PRODUCTO SET descripcion = ?, categoria = ?, stock = ?, valor = ? WHERE codproducto = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getDescripcion());
            pstmt.setString(2, producto.getCategoria());
            pstmt.setString(3, producto.getStock());
            pstmt.setString(4, producto.getValor());
            pstmt.setString(5, producto.getCodProducto());

            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto en la base de datos: " + e.getMessage());
        }
    }
}