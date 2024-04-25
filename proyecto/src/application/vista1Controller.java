package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class vista1Controller {
	@FXML
	private TextField userText;
	@FXML
	private PasswordField passText;
	@FXML
	private Button btnLogin;

	// Event Listener on Button[#btnLogin].onMouseClicked
	@FXML
	public void btnLogin(MouseEvent event) throws IOException {
		
		String username = userText.getText();
        String pass = passText.getText();

        try {
            File file = new File("credenciales.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] credentials = scanner.nextLine().split(",");
                if (credentials[0].equals(username) && credentials[1].equals(pass)) {
                    System.out.println("Inicio de sesión exitoso");
                    
            		//cerrar anterior
            		Stage currenStage = (Stage) btnLogin.getScene().getWindow();
            		//currenStage.close();
            		//abrir ventana
            		FXMLLoader loader =new FXMLLoader(getClass().getResource("vistaBotones.fxml"));
            		//Stage stage = new Stage();
            		currenStage.setScene(new Scene(loader.load()));
            		currenStage.show();
                    return;
                    
                }
                
            }
            System.out.println("Credenciales incorrectas");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("contraseña errada");
            error.setTitle("error");
            error.show();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        
        }
    }

		

			
			// Event Listener on Button[#secBtn].on
	
}
