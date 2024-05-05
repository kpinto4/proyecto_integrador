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

public class vista4Controller implements Initializable{
	@FXML
	private TableView<Usuario> Tablaview;
	@FXML
	private TableColumn<Usuario, String> CedUsuarioColumn;
	@FXML
	private TableColumn<Usuario, String> NombreColumn;
	@FXML
	private TableColumn<Usuario, String> ApellidoColumn;
	@FXML
	private TableColumn<Usuario, String> DireccionColumn;
	@FXML
	private TableColumn<Usuario, String> TelefonoColumn;
	@FXML
	private TableColumn<Usuario, String> UsuarioColumn;
	@FXML
	private TableColumn<Usuario, String> ContraseñaColumn;
	@FXML
	private TableColumn<Usuario, String> CargoColumn;
	@FXML
	private Button cargarBtn;
	@FXML
	private Button crearBtn;
	@FXML
	private Button eliminarBtn;
	@FXML
	private Button actualizarBtn;
	@FXML
	private TextField TextCedUsuario;
	@FXML
	private TextField TextCity;
	@FXML
	private TextField TextNombre;
	@FXML
	private TextField TextApellido;
	@FXML
	private TextField TextDireccion;
	@FXML
	private TextField TextTelefono;
	@FXML
	private TextField TextUsuario;
	@FXML
	private TextField TextContraseña;
	@FXML
	private TextField TextCargo;
	
	
	ObservableList<Usuario> Datos = FXCollections.observableArrayList();
    
    DatosUsuario data;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data= new DatosUsuario();
        this.CedUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("CedUsuario"));
        this.NombreColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Nombre"));
        this.ApellidoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Apellido"));
        this.DireccionColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Direccion"));
        this.TelefonoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Telefono"));
        this.UsuarioColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Usuario"));
        this.ContraseñaColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Contraseña"));
        this.CargoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Cargo"));
        
        
        Tablaview.setItems(this.Datos);
        this.CedUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ApellidoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DireccionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.TelefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.UsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ContraseñaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.CargoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                       
    }
    
    @FXML
    public void Tablaview(MouseEvent event) {
        Usuario seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextCedUsuario.setText(seleccion.getCedUsuario());
            this.TextNombre.setText(seleccion.getNombre());
            this.TextApellido.setText(seleccion.getApellido());
            this.TextDireccion.setText(seleccion.getDireccion());
            this.TextTelefono.setText(seleccion.getTelefono());
            this.TextUsuario.setText(seleccion.getUsuario());
            this.TextContraseña.setText(seleccion.getContraseña());
            this.TextCargo.setText(seleccion.getCargo());
        }
    }

        
    

	// Event Listener on TableColumn[#CedUsuarioColumn].onEditCommit
	@FXML
	public void OnCedUsuario(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCedUsuario(event.getNewValue());
    }
	// Event Listener on TableColumn[#NombreColumn].onEditCommit
	@FXML
	public void OnNombre(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setNombre(event.getNewValue());
    }
	// Event Listener on TableColumn[#ApellidoColumn].onEditCommit
	@FXML
	public void OnApellido(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setApellido(event.getNewValue());
    }
	// Event Listener on TableColumn[#DireccionColumn].onEditCommit
	@FXML
	public void OnDireccion(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDireccion(event.getNewValue());
    }
	// Event Listener on TableColumn[#TelefonoColumn].onEditCommit
	@FXML
	public void OnTelefono(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setTelefono(event.getNewValue());
    }
	// Event Listener on TableColumn[#UsuarioColumn].onEditCommit
	@FXML
	public void OnUsuario(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setUsuario(event.getNewValue());
    }
	// Event Listener on TableColumn[#ContraseñaColumn].onEditCommit
	@FXML
	public void OnContraseña(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setContraseña(event.getNewValue());
    }
	// Event Listener on TableColumn[#CargoColumn].onEditCommit
	@FXML
	public void OnCargo(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCargo(event.getNewValue());
    }
	// Event Listener on Button[#cargarBtn].onMouseClicked
	@FXML
	public void cargarBtn(MouseEvent event) {
        LinkedList<Usuario> data1 = data.getDatos();
        Datos.setAll(data1);
    }
	// Event Listener on Button[#crearBtn].onMouseClicked
	@FXML
	public void crearBtn(MouseEvent event) {
	        Usuario nuevoUsuario = new Usuario(
	            TextCedUsuario.getText(),
	            TextNombre.getText(),
	            TextApellido.getText(),
	            TextDireccion.getText(),
	            TextTelefono.getText(),
	            TextUsuario.getText(),
	            TextContraseña.getText(),
	            TextCargo.getText()
	            
	        );
	        data.guardarUsuario(nuevoUsuario);
	        Datos.add(nuevoUsuario);
	        limpiarCamposTexto();
	    }
	// Event Listener on Button[#eliminarBtn].onMouseClicked
	@FXML
	public void eliminarBtn(MouseEvent event) {
        Usuario seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            data.eliminarUsuario(seleccion.getCedUsuario());
            Datos.remove(seleccion);
            limpiarCamposTexto();
        } else {
            System.out.println("Error: No se ha seleccionado ningún usuario para eliminar.");
        }
    }
	// Event Listener on Button[#actualizarBtn].onMouseClicked
	@FXML
	public void actualizarBtn(MouseEvent event) {
        Usuario seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setCedUsuario(TextCedUsuario.getText());
            seleccion.setNombre(TextNombre.getText());
            seleccion.setApellido(TextApellido.getText());
            seleccion.setDireccion(TextDireccion.getText());
            seleccion.setTelefono(TextTelefono.getText());
            seleccion.setUsuario(TextUsuario.getText());
            seleccion.setContraseña(TextContraseña.getText());
            seleccion.setCargo(TextCargo.getText());


            data.actualizarUsuario(seleccion);
            limpiarCamposTexto();
            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
        } else {
            System.out.println("Error: No se ha seleccionado ningún usuario para actualizar.");
        }
    }
    
    private void limpiarCamposTexto() {
        TextCedUsuario.clear();
        TextNombre.clear();
        TextApellido.clear();
        TextDireccion.clear();
        TextTelefono.clear();
        TextUsuario.clear();
        TextContraseña.clear();
        TextCargo.clear();
        
        
       }
}
