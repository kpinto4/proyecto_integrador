package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteFac {
    private Connection conexion;

    public ClienteFac(Connection conexion) {
        this.conexion = conexion;
    }

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

        return clientes;
    }
}