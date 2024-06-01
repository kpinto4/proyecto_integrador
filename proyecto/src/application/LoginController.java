package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginController {
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passText;

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "BASE";
    private static final String PASSWORD = "BASE";

    @FXML
    public void btnLogin(MouseEvent event) throws IOException {
        String username = userText.getText();
        String pass = passText.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT usuario, cargo FROM USUARIO WHERE usuario = ? AND contrasena = ?")) {
            st.setString(1, username);
            st.setString(2, pass);
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    String cargo = result.getString("cargo");

                    // Establecer el nombre y el cargo del usuario en Sesion
                    Sesion.setNombreUsuario(username);
                    Sesion.setCargoUsuario(cargo);

                    System.out.println("Inicio de sesión exitoso");

                    // Abrir la nueva ventana (puede ser el menú principal)
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Cerrar la ventana actual
                    Stage currentStage = (Stage) userText.getScene().getWindow();
                    currentStage.close();
                } else {
                    System.out.println("Credenciales incorrectas");

                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("Credenciales incorrectas");
                    error.setContentText("Por favor, verifique sus credenciales e intente de nuevo.");
                    error.setTitle("Error de inicio de sesión");
                    error.show();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error de base de datos");
            error.setContentText("No se pudo conectar con la base de datos. Por favor, intente más tarde.");
            error.setTitle("Error de conexión");
            error.show();
        }
    }
    @FXML
	public void InLogin(KeyEvent event) {
	      if (event.getCode() == KeyCode.ENTER) {
	            try {
	                btnLogin(null); // Llamar al método de inicio de sesión
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}