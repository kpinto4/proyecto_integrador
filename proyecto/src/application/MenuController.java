package application;
/*
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private ImageView btnFacturacion;
	@FXML
	private ImageView btnClientes;
	@FXML
	private ImageView btnProductos;
	@FXML
	private ImageView btnUsuarios;
	@FXML
	private ImageView btnInformes;
	@FXML
	private Button btnCerrar;

	// Event Listener on ImageView[#btnFacturacion].onMouseClicked
	@FXML
	public void btnFacturacion(MouseEvent event) throws IOException {
		
		
		System.out.println("abrir facturacion");
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        
        // Abrir la ventana de facturacion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista2.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
		
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#btnClientes].onMouseClicked
	@FXML
	public void btnClientes(MouseEvent event) throws IOException {
		
		System.out.println("abrir clientes");
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        
        // Abrir la ventana de clientes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cliente.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#btnProductos].onMouseClicked
	@FXML
	public void btnProductos(MouseEvent event) throws IOException {
		
		System.out.println("abrir productos");
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        
        // Abrir la ventana de productos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Producto.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
		
		
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#btnUsuarios].onMouseClicked
	@FXML
	public void btnUsuarios(MouseEvent event) throws IOException {
		System.out.println("abrir usuarios");
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        
        // Abrir la ventana de usuarios
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Usuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
     	}
	// Event Listener on ImageView[#btnInformes].onMouseClicked
	@FXML
	public void btnInformes(MouseEvent event) throws IOException {
		
		System.out.println("abrir informes");

     	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        
        // Abrir la ventana de informes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista6.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
		
		
		// TODO Autogenerated
	
	// Event Listener on Button[#btnCerrar].onMouseClicked
	@FXML
	public void btnCerrar(MouseEvent event) throws IOException {
		// TODO Autogenerated
		
		System.out.println("cerrar secion");
        
        Stage currenStage = (Stage) btnCerrar.getScene().getWindow();
		//currenStage.close();
		//abrir ventana
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Login.fxml"));
		//Stage stage = new Stage();
		currenStage.setScene(new Scene(loader.load()));
		currenStage.show();
        return;
	}
}
*/



import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class MenuController {
    @FXML
    private ImageView btnFacturacion;
    @FXML
    private ImageView btnClientes;
    @FXML
    private ImageView btnProductos;
    @FXML
    private ImageView btnUsuarios;
    @FXML
    private ImageView btnInformes;
    @FXML
    private Button btnCerrar;
    @FXML
    private String cargoUsuario; // Variable para almacenar el cargo del usuario
    
    
    
   // Método para establecer el cargo del usuario
    public void setCargoUsuario(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }
    
 // Método para inicializar el controlador
    @FXML
    public void initialize() {
        cargoUsuario = Sesion.getCargoUsuario(); // Obtener el cargo del usuario desde Sesion
    }

    // Evento para el botón de facturación
    @FXML
    public void btnFacturacion(MouseEvent event) throws IOException {
        System.out.println("abrir facturación");

        // Verificar el cargo del usuario antes de abrir la ventana
        if (cargoUsuario != null && (cargoUsuario.equals("administrador") || cargoUsuario.equals("vendedor"))) {
            abrirVentana("Facturacion.fxml");
        } else {
            System.out.println("Acceso denegado.");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("No tienes acceso esta funcion ");
            error.setContentText("Por favor, ingresa con un usuario adecuado");
            error.setTitle("Acceso denegado");
            error.show();
        }
    }

    // Evento para el botón de clientes
    @FXML
    public void btnClientes(MouseEvent event) throws IOException {
        System.out.println("abrir clientes");

        // Verificar el cargo del usuario antes de abrir la ventana
        if (cargoUsuario != null && (cargoUsuario.equals("administrador") || cargoUsuario.equals("vendedor"))) {
            abrirVentana("Cliente.fxml");
        } else {
            System.out.println("Acceso denegado.");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("No tienes acceso esta funcion ");
            error.setContentText("Por favor, ingresa con un usuario adecuado");
            error.setTitle("Acceso denegado");
            error.show();
        }
    }

    // Evento para el botón de productos
    @FXML
    public void btnProductos(MouseEvent event) throws IOException {
        System.out.println("abrir productos");

        // Verificar el cargo del usuario antes de abrir la ventana
        if (cargoUsuario != null && cargoUsuario.equals("administrador")) {
            abrirVentana("Producto.fxml");
        } else {         	
            System.out.println("Acceso denegado. Permiso de administrador requerido.");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("No tienes acceso esta funcion ");
            error.setContentText("Permiso de administrador requerido.");
            error.setTitle("Acceso denegado");
            error.show();
        }
    }

    // Evento para el botón de usuarios
    @FXML
    public void btnUsuarios(MouseEvent event) throws IOException {
        System.out.println("abrir usuarios");

        // Verificar el cargo del usuario antes de abrir la ventana
        if (cargoUsuario != null && cargoUsuario.equals("administrador")) {
            abrirVentana("Usuario.fxml");
        } else {
            System.out.println("Acceso denegado. Permiso de administrador requerido.");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("No tienes acceso esta funcion ");
            error.setContentText("Permiso de administrador requerido.");
            error.setTitle("Acceso denegado");
            error.show();
        }
    }

    // Evento para el botón de informes
    @FXML
    public void btnInformes(MouseEvent event) throws IOException {
        System.out.println("abrir informes");

        // Verificar el cargo del usuario antes de abrir la ventana
        if (cargoUsuario != null && cargoUsuario.equals("administrador")) {
            abrirVentana("Informe.fxml");
        } else {
            System.out.println("Acceso denegado. Permiso de administrador requerido.");
            
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("No tienes acceso esta funcion ");
            error.setContentText("Permiso de administrador requerido.");
            error.setTitle("Acceso denegado");
            error.show();
        }
    }

    // Método para abrir una nueva ventana
    private void abrirVentana(String fxmlPath) throws IOException {
        Stage primaryStage = (Stage) ((Node) btnFacturacion).getScene().getWindow();
        primaryStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    // Evento para el botón de cerrar sesión
    @FXML
    public void btnCerrar(MouseEvent event) throws IOException {
        System.out.println("cerrar sesión");

        Stage currentStage = (Stage) btnCerrar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        currentStage.setScene(new Scene(loader.load()));
        currentStage.show();
    }
}