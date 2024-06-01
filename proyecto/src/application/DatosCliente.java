package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosCliente {


    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "proto";
    private static final String PASSWORD = "proto";
/*
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";*/




    public LinkedList<Cliente> getDatos() {
        LinkedList<Cliente> data = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM CLIENTE");
             ResultSet result = st.executeQuery()) {

            while (result.next()) {
                Cliente Cliente = new Cliente(
                        result.getString("Cedula"),
                        result.getString("Nombre"),
                        result.getString("Direccion"),
                        result.getString("Telefono")
                );
                data.add(Cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (Cedula, Nombre, Direccion, Telefono) VALUES (?,  ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getCedula());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setString(4, cliente.getTelefono());
            

            pstmt.executeUpdate();
            System.out.println("cliente guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el cliente en la base de datos: " + e.getMessage());
        }
    
    }

    public void eliminarCliente(String Cedula) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("DELETE FROM CLIENTE WHERE cedula = ?")) {

            st.setString(1, Cedula);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET nombre = ?, direccion = ?, telefono = ? WHERE cedula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCedula());

            pstmt.executeUpdate();
            System.out.println("cliente actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente en la base de datos: " + e.getMessage());
        }
    }

    public boolean existeCliente(String cedula) {
        String sql = "SELECT COUNT(*) FROM CLIENTE WHERE Cedula = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cedula);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}




