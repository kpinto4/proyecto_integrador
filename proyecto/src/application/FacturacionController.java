package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

<<<<<<< HEAD
//ana estuvo aqui
=======

>>>>>>> origin/KevinS
public class FacturacionController implements Initializable {

    @FXML
    private TextField cedulaClienteText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Método para verificar la cédula en la base de datos
    @FXML
    public void verificarCedula(ActionEvent event) {
        String cedula = cedulaClienteText.getText();
        if (cedula != null && !cedula.isEmpty()) {
            boolean cedulaValida = validarCedulaEnBD(cedula);
            if (cedulaValida) {
                System.out.println("Cédula encontrada en la base de datos.");
                // Aquí puedes mostrar un mensaje o realizar otras acciones
            } else {
                System.out.println("Cédula no encontrada en la base de datos.");
                // Aquí puedes mostrar un mensaje o realizar otras acciones
            }
        } else {
            System.out.println("Ingrese una cédula antes de verificar.");
            // Aquí puedes mostrar un mensaje o realizar otras acciones
        }
    }

    // Método simulado para validar la cédula en la base de datos
    private boolean validarCedulaEnBD(String cedula) {
        // Aquí iría la lógica real para verificar la cédula en la base de datos
        // Por ahora, simplemente devolvemos true si la cédula tiene un formato válido
        return cedula.matches("\\d{10}");
    }

    // Otros métodos y código del controlador
}


/*
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class FacturacionController {
	@FXML
	private MenuButton tipoPagoMenu;
	@FXML
	private TextField cedulaClienteText;
	@FXML
	private TextField referenciaText;
	@FXML
	private TextField numeroFcaturaText;
	@FXML
	private TextField nombreClienteText;
	@FXML
	private TextField totalPagoText;
	@FXML
	private TableView Tablaview;
	@FXML
	private TableColumn ReferenciaColumn;
	@FXML
	private TableColumn DescripcionColumn;
	@FXML
	private TableColumn CategoriaColumn;
	@FXML
	private TableColumn CantidadColumn;
	@FXML
	private TableColumn ValorUColumn;
	@FXML
	private TableColumn ValorTColumn;
	@FXML
	private TextField cantidadText;
	@FXML
	private ImageView btnCerrar;
	@FXML
	private Button buscarBtn;
	@FXML
	private Button generarBtn;
	@FXML
	private Button eliminarBtn;
	@FXML
	private Button actualizarBtn;

	// Event Listener on TableColumn[#ReferenciaColumn].onEditCommit
	@FXML
	public void ReferenciaColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on TableColumn[#DescripcionColumn].onEditCommit
	@FXML
	public void DescripcionColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on TableColumn[#CategoriaColumn].onEditCommit
	@FXML
	public void CategoriaColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on TableColumn[#CantidadColumn].onEditCommit
	@FXML
	public void CantidadColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on TableColumn[#ValorUColumn].onEditCommit
	@FXML
	public void ValorUColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on TableColumn[#ValorTColumn].onEditCommit
	@FXML
	public void ValorTColumn(? event) {
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#btnCerrar].onMouseClicked
	@FXML
	public void btnCerrar(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#buscarBtn].onMouseClicked
	@FXML
	public void buscarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#generarBtn].onMouseClicked
	@FXML
	public void generarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#eliminarBtn].onMouseClicked
	@FXML
	public void eliminarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#actualizarBtn].onMouseClicked
	@FXML
	public void actualizarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
}
*/