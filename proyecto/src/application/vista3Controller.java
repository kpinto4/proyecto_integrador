package application;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class vista3Controller implements Initializable{
	@FXML
	private TableView<Cliente> Tablaview;
	@FXML
	private TableColumn<Cliente, String> CedulaColumn;
	@FXML
	private TableColumn<Cliente, String> NombreColumn;
	@FXML
	private TableColumn<Cliente, String> ApellidoColumn;
	@FXML
	private TableColumn<Cliente, String> DireccionColumn;
	@FXML
	private TableColumn<Cliente, String> TelefonoColumn;
	@FXML
	private TextField TextCedula;
	@FXML
	private TextField TextNombre;
	@FXML
	private TextField TextApellido;
	@FXML
	private TextField TextDireccion;
	@FXML
	private TextField TextTelefono;
	@FXML
	private Button cargarBtn;
	@FXML
	private Button crearBtn;
	@FXML
	private Button eliminarBtn;
	@FXML
	private Button actualizarBtn;
	
    ObservableList<Cliente> Datos = FXCollections.observableArrayList();
    
    DatosCliente data;

	// Event Listener on TableView[#Tablaview].onMouseClicked
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data= new DatosCliente();
        this.CedulaColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cedula"));
        this.NombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nombre"));
        this.ApellidoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Apellido"));
        this.DireccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Direccion"));
        this.TelefonoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefono"));
        
        Tablaview.setItems(this.Datos);
        this.CedulaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ApellidoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DireccionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.TelefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                       
    }
    
    @FXML
    public void Tablaview(MouseEvent event) {
        Cliente seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextCedula.setText(seleccion.getCedula());
            this.TextNombre.setText(seleccion.getNombre());
            this.TextApellido.setText(seleccion.getApellido());
            this.TextDireccion.setText(seleccion.getDireccion());
            this.TextTelefono.setText(seleccion.getTelefono());
           

        }
    }
	// Event Listener on TableColumn[#CedulaColumn].onEditCommit
	@FXML
	public void OnCedula(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCedula(event.getNewValue());
    }

	// Event Listener on TableColumn[#NombreColumn].onEditCommit
	@FXML
	public void OnNombre(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setNombre(event.getNewValue());
    }
	// Event Listener on TableColumn[#ApellidoColumn].onEditCommit
	@FXML
	public void OnApellido(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setApellido(event.getNewValue());
    }
	// Event Listener on TableColumn[#DireccionColumn].onEditCommit
	@FXML
	public void OnDireccion(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDireccion(event.getNewValue());
    }
	// Event Listener on TableColumn[#CorreoColumn].onEditCommit
	@FXML
	public void OnTelefono(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setTelefono(event.getNewValue());
    }
	// Event Listener on Button[#cargarBtn].onMouseClicked
	@FXML
	public void cargarBtn(MouseEvent event) {
        LinkedList<Cliente> data1 = data.getDatos();
        Datos.setAll(data1);
    }
	// Event Listener on Button[#crearBtn].onMouseClicked
	@FXML
	public void crearBtn(MouseEvent event) {
	        Cliente nuevoCliente = new Cliente(
	            TextCedula.getText(),
	            TextNombre.getText(),
	            TextApellido.getText(),
	            TextDireccion.getText(),
	            TextTelefono.getText()
	        );
	        data.guardarCliente(nuevoCliente);
	        Datos.add(nuevoCliente);
	        limpiarCamposTexto();
	    }
	// Event Listener on Button[#eliminarBtn].onMouseClicked
	@FXML
	public void eliminarBtn(MouseEvent event) {
        Cliente seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            data.eliminarCliente(seleccion.getCedula());
            Datos.remove(seleccion);
            limpiarCamposTexto();
        } else {
            System.out.println("Error: No se ha seleccionado ningún cliente para eliminar.");
        }
    }
	// Event Listener on Button[#actualizarBtn].onMouseClicked
	@FXML
	public void actualizarBtn(MouseEvent event) {
        Cliente seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setCedula(TextCedula.getText());
            seleccion.setNombre(TextNombre.getText());
            seleccion.setApellido(TextApellido.getText());
            seleccion.setDireccion(TextDireccion.getText());
            seleccion.setTelefono(TextTelefono.getText());

            data.actualizarCliente(seleccion);
            limpiarCamposTexto();
            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
        } else {
            System.out.println("Error: No se ha seleccionado ningún cliente para actualizar.");
        }
    }
    
    private void limpiarCamposTexto() {
        TextCedula.clear();
        TextNombre.clear();
        TextApellido.clear();
        TextDireccion.clear();
        TextTelefono.clear();
       }
}