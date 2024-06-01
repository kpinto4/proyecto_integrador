package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ClienteFac proporciona métodos para interactuar con la tabla de clientes en la base de datos.
 * @author Kevin Santiago
 *
 */
public class ClienteFac {
    private Connection conexion;

    /**
     * Constructor de la clase ClienteFac.
     * Inicializa la conexión a la base de datos. 
     * @param conexion La conexión a la base de datos.
     */
    public ClienteFac(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Este metodo recupera la lista de clientes almacenados en la base de datos.
     * Realiza una consulta a la base de datos para obtener todos los clientes. 
     * @return Una lista de todos los clientes que estan en la base de datos.
     */
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String consulta = "SELECT * FROM cliente";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
             ResultSet resultado = declaracion.executeQuery()) {
            while (resultado.next()) {
                Cliente cliente = new Cliente(
                    resultado.getString("Cedula"),
                    resultado.getString("Nombre"),
                    resultado.getString("Direccion"),
                    resultado.getString("Telefono")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Devolver la lista de clientes
        return clientes;
    }
}