package application;
/*
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    private Button btnLogin;

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";

    @FXML
    public void btnLogin(MouseEvent event) throws IOException {
        String username = userText.getText();
        String pass = passText.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM USUARIO WHERE usuario = ? AND contrasena = ?")) {
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet result = st.executeQuery();

            if (result.next()) {
                System.out.println("Inicio de sesión exitoso");

                // Cerrar la ventana actual
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.close();

                // Abrir la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("Credenciales incorrectas");

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Credenciales incorrectas");
                error.setContentText("Por favor, verifique sus credenciales e intente de nuevo.");
                error.setTitle("Error de inicio de sesión");
                error.show();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}*/





/* este esta bueno
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    private Button btnLogin;

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";

    private String cargoUsuario; // Variable para almacenar el cargo del usuario

    // Método para establecer el cargo del usuario
    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    @FXML
    public void btnLogin(MouseEvent event) throws IOException {
        String username = userText.getText();
        String pass = passText.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT usuario, cargo FROM USUARIO WHERE usuario = ? AND contrasena = ?")) {
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet result = st.executeQuery();

            if (result.next()) {
                String cargo = result.getString("cargo");
                setCargoUsuario(cargo); // Establecer el cargo del usuario en el controlador

                System.out.println("Inicio de sesión exitoso");

                // Cerrar la ventana actual
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.close();

                // Abrir la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                MenuController controller = loader.getController(); // Obtener el controlador de la ventana de menú
                controller.setCargoUsuario(cargo); // Pasar el cargo del usuario al controlador de menú
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("Credenciales incorrectas");

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Credenciales incorrectas");
                error.setContentText("Por favor, verifique sus credenciales e intente de nuevo.");
                error.setTitle("Error de inicio de sesión");
                error.show();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}*/



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
    private static final String USER = "INNOVATECH";
    private static final String PASSWORD = "INNOVATECH";

    @FXML
    public void btnLogin(MouseEvent event) throws IOException {
        String username = userText.getText();
        String pass = passText.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT usuario, cargo FROM USUARIO WHERE usuario = ? AND contrasena = ?")) {
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet result = st.executeQuery();

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
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}