package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatosUsuario {
	 	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	    private static final String USER = "INNOVATECH";
	    private static final String PASSWORD = "INNOVATECH";

	    public LinkedList<Usuario> getDatos() {
	        LinkedList<Usuario> data = new LinkedList<>();
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement st = conn.prepareStatement("SELECT * FROM USUARIO");
	             ResultSet result = st.executeQuery()) {

	            while (result.next()) {
	                Usuario Usuario = new Usuario(
	                        result.getString("CedUsuario"),
	                        result.getString("Nombre"),
	                        result.getString("Apellido"),
	                        result.getString("Direccion"),
	                        result.getString("Telefono"),
	                        result.getString("usuario"),
	                        result.getString("Contrasena"),
	                        result.getString("Cargo")
	                );
	                data.add(Usuario);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }

	    public void guardarUsuario(Usuario usuario) {
	        String sql = "INSERT INTO USUARIO (CEDUSUARIO, NOMBRE, APELLIDO, DIRECCION, TELEFONO, USUARIO, CONTRASENA, CARGO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, usuario.getCedUsuario());
	            pstmt.setString(2, usuario.getNombre());
	            pstmt.setString(3, usuario.getApellido());
	            pstmt.setString(4, usuario.getDireccion());
	            pstmt.setString(5, usuario.getTelefono());
	            pstmt.setString(6, usuario.getUsuario());
	            pstmt.setString(7, usuario.getContrasena());
	            pstmt.setString(8, usuario.getCargo());
	            

	            pstmt.executeUpdate();
	            System.out.println("usuario guardado correctamente en la base de datos.");
	        } catch (SQLException e) {
	            System.out.println("Error al guardar el usuario en la base de datos: " + e.getMessage());
	        }
	    
	    }

	    public void eliminarUsuario(String CedUsaurio) {
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement st = conn.prepareStatement("DELETE FROM USUARIO WHERE cedusuario = ?")) {

	            st.setString(1, CedUsaurio);
	            st.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void actualizarUsuario(Usuario usuario) {
	        String sql = "UPDATE USUARIO SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, usuario = ?, contrasena = ?, cargo = ? WHERE cedusuario = ?";

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, usuario.getNombre());
	            pstmt.setString(2, usuario.getApellido());
	            pstmt.setString(3, usuario.getDireccion());
	            pstmt.setString(4, usuario.getTelefono());
	            pstmt.setString(5, usuario.getUsuario());
	            pstmt.setString(6, usuario.getContrasena());
	            pstmt.setString(7, usuario.getCargo());
	            pstmt.setString(8, usuario.getCedUsuario());

	            pstmt.executeUpdate();
	            System.out.println("usuario actualizado correctamente en la base de datos.");
	        } catch (SQLException e) {
	            System.out.println("Error al actualizar el usuario en la base de datos: " + e.getMessage());
	        }
	    }


	}

