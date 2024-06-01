package application;

/**
 * - Importaciones necesarias para el desarrollo.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * - La clase DatosUsuario sirve para manejar los procedimientos correspondientes en la base de datos.
 * @author Kevin Santiago
 *
 */
public class DatosUsuario {
	 	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	    private static final String USER = "BASE";
	    private static final String PASSWORD = "BASE";
	    
	    /**
	     * - Obtiene los datos de todos los usuarios desde la base de datos.
	     * @return LinkedList<Usuario> lista de usuarios.
	     */
	    public LinkedList<Usuario> getDatos() {
	        LinkedList<Usuario> data = new LinkedList<>();
	        String query = "SELECT e.cedula, e.nombre, e.direccion, e.telefono, " +
	                       "u.contrasena, u.cargo, u.usuario " +
	                       "FROM empleado e " +
	                       "JOIN usuario u ON e.cedula = u.cedula";
	        
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement st = conn.prepareStatement(query);
	             ResultSet result = st.executeQuery()) {

	            while (result.next()) {
	                Usuario usuario = new Usuario(
	                    result.getString("cedula"), // cedula from empleado
	                    result.getString("nombre"), // nombre from empleado
	                    result.getString("direccion"), // direccion from empleado
	                    result.getString("telefono"), // telefono from empleado
	                    result.getString("usuario"), // usuario from usuario
	                    result.getString("contrasena"), // contrasena from usuario
	                    result.getString("cargo") // cargo from usuario
	                );
	                data.add(usuario);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }


	    public void guardarUsuario(Usuario usuario) {
	        // Inserción en la tabla empleado
	        String sqlEmpleado = "INSERT INTO EMPLEADO (CEDULA, NOMBRE, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?)";
	        // Inserción en la tabla usuario
	        String sqlUsuario = "INSERT INTO USUARIO (USUARIO_ID, CEDULA, USUARIO, CONTRASENA, CARGO) VALUES (?, ?, ?, ?, ?)"; // Agregar un parámetro adicional para el campo USUARIO_ID

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            try (PreparedStatement pstmtEmpleado = conn.prepareStatement(sqlEmpleado);
	                 PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {

	                // Para la tabla empleado
	                pstmtEmpleado.setString(1, usuario.getCedula());
	                pstmtEmpleado.setString(2, usuario.getNombre());
	                pstmtEmpleado.setString(3, usuario.getDireccion());
	                pstmtEmpleado.setString(4, usuario.getTelefono());
	                pstmtEmpleado.executeUpdate();

	                // Para la tabla usuario
	                pstmtUsuario.setString(1, usuario.getCedula()); // Asumo que el ID de usuario es la cédula
	                pstmtUsuario.setString(2, usuario.getCedula());
	                pstmtUsuario.setString(3, usuario.getUsuario());
	                pstmtUsuario.setString(4, usuario.getContrasena());
	                pstmtUsuario.setString(5, usuario.getCargo());
	                pstmtUsuario.executeUpdate();

	                System.out.println("Usuario guardado correctamente en la base de datos.");
	            } catch (SQLException e) {
	                System.out.println("Error al guardar el usuario en la base de datos: " + e.getMessage());
	                // Aquí podrías agregar lógica para revertir la inserción en la tabla empleado si falla la inserción en la tabla usuario
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

public void eliminarUsuario(String cedula) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
        try (PreparedStatement stUsuario = conn.prepareStatement("DELETE FROM USUARIO WHERE cedula = ?");
             PreparedStatement stEmpleado = conn.prepareStatement("DELETE FROM EMPLEADO WHERE cedula = ?")) {

            // Eliminar de la tabla usuario
            stUsuario.setString(1, cedula);
            stUsuario.executeUpdate();

            // Eliminar de la tabla empleado
            stEmpleado.setString(1, cedula);
            stEmpleado.executeUpdate();

            System.out.println("Usuario eliminado correctamente de la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario de la base de datos: " + e.getMessage());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void actualizarUsuario(Usuario usuario) {
    // Actualización en la tabla empleado
    String sqlEmpleado = "UPDATE EMPLEADO SET nombre = ?, direccion = ?, telefono = ? WHERE cedula = ?";
    // Actualización en la tabla usuario
    String sqlUsuario = "UPDATE USUARIO SET usuario = ?, contrasena = ?, cargo = ? WHERE cedula = ?";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
        try (PreparedStatement pstmtEmpleado = conn.prepareStatement(sqlEmpleado);
             PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {

            // Para la tabla empleado
            pstmtEmpleado.setString(1, usuario.getNombre());
            pstmtEmpleado.setString(2, usuario.getDireccion());
            pstmtEmpleado.setString(3, usuario.getTelefono());
            pstmtEmpleado.setString(4, usuario.getCedula());
            pstmtEmpleado.executeUpdate();

            // Para la tabla usuario
            pstmtUsuario.setString(1, usuario.getUsuario());
            pstmtUsuario.setString(2, usuario.getContrasena());
            pstmtUsuario.setString(3, usuario.getCargo());
            pstmtUsuario.setString(4, usuario.getCedula());
            pstmtUsuario.executeUpdate();

            System.out.println("Usuario actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario en la base de datos: " + e.getMessage());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean existeUsuario(String cedula) {
    String sql = "SELECT COUNT(*) FROM USUARIO WHERE CEDULA = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, cedula);
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