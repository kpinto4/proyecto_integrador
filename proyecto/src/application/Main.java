package application;

/**
 * Importa las clases necesarias.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * La clase Main es la clase principal que inicia la aplicación.
 * Esta clase carga el archivo FXML de la pantalla de inicio de sesión y lo muestra en la ventana principal de la aplicación.
 * @author Kevin Santiago
 *
 */
public class Main extends Application {
	
	/**
	 * Método principal que inicia la aplicación.
	 * Carga y muestra la interfaz de usuario de inicio de sesión.
	 * @param primaryStage El escenario principal de la aplicación.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método principal que inicia la aplicación.
	 * Llama al método launch() de la clase Application para iniciar la aplicación JavaFX.
	 * @param args Los argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

