package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DatosProducto {

    public LinkedList<Producto> getDatos() {
    	Producto a = null;
		LinkedList<Producto> data = new LinkedList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.254.215:1521:orcl", "INNOVATECH","INNOVATECH");
			Statement st= conn.createStatement();
			String query = "select * from FacturasVenta";		
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				a = new Producto(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
				data.add(a);
    	
			}
        } catch (SQLException e) {
            // Manejar cualquier excepción de SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        }
        return data; // Devolver la lista de productos
    }

    // Método para guardar un nuevo producto en la base de datos
    public void guardarProducto(Producto producto) {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.254.215:1521:orcl", "programming","programming");
            // Crear una declaración SQL
            Statement st = conn.createStatement();
            // Consulta para insertar el nuevo producto en la tabla Productos
            String query = "INSERT INTO Productos (referencia, descripcion, categoria, cantidad, valor, stock) VALUES ('"
                    + producto.getReferencia() + "', '" + producto.getDescripcion() + "', '" + producto.getCategoria()
                    + "', " + producto.getCantidad() + ", " + producto.getValor() + ", " + producto.getStock() + ")";
            // Ejecutar la consulta de inserción
            st.executeUpdate(query);

            // Cerrar la declaración y la conexión
            st.close();
            conn.close();
        } catch (SQLException e) {
            // Manejar cualquier excepción de SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto de la base de datos
    public void eliminarProducto(Producto producto) {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.254.215:1521:orcl", "programming","programming");
            // Crear una declaración SQL
            Statement st = conn.createStatement();
            // Consulta para eliminar el producto con la referencia dada
            String query = "DELETE FROM Productos WHERE referencia = '" + producto.getReferencia() + "'";
            // Ejecutar la consulta de eliminación
            st.executeUpdate(query);

            // Cerrar la declaración y la conexión
            st.close();
            conn.close();
        } catch (SQLException e) {
            // Manejar cualquier excepción de SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        }
    }


}
