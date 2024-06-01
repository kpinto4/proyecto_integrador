package application;

/**
 * Importa las clases necesarias.
 */
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

/**
 * Esta clase sirve para ver y manipular las acciones de la ventana Cliente.
 * @author Kevin Santiago
 *
 */
public class ClienteController implements Initializable{
	@FXML
	private TableView<Cliente> Tablaview;
	@FXML
	private TableColumn<Cliente, String> CedulaColumn;
	@FXML
	private TableColumn<Cliente, String> NombreColumn;
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
	@FXML
	private ImageView btnCerrar;
	
	/**
	 * Declaración y creación de una lista observable de objetos Cliente.
	 */
    ObservableList<Cliente> Datos = FXCollections.observableArrayList();
    DatosCliente data;

	/**
	 * Método de inicialización del controlador.
	 */
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data= new DatosCliente();
        this.CedulaColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Cedula"));
        this.NombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nombre"));
        this.DireccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Direccion"));
        this.TelefonoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefono"));
        
        Tablaview.setItems(this.Datos);
        this.CedulaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //this.ApellidoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DireccionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.TelefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());                  
    }
    
    /**
     * Metodo para mostrar los datos del cliente seleccionado en los campos de texto.
     * @param event El evento de clic del mouse.
     */
    @FXML
    public void Tablaview(MouseEvent event) {
        Cliente seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextCedula.setText(seleccion.getCedula());
            this.TextNombre.setText(seleccion.getNombre());
            this.TextDireccion.setText(seleccion.getDireccion());
            this.TextTelefono.setText(seleccion.getTelefono());
        }
    }
	/**
	 * Método para manejar el evento de edición de la columna de cedula.
	 * @param event El evento de edición de la celda.
	 */
	@FXML
	public void OnCedula(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCedula(event.getNewValue());
    }

	/**
	 * Método para manejar el evento de edición de la columna de nombre.
	 * @param event El evento de edición de la celda.
	 */
	@FXML
	public void OnNombre(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setNombre(event.getNewValue());
    }
	
	/**
	 * Método para manejar el evento de edición de la columna de direccion.
	 * @param event El evento de edición de la celda.
	 */
	@FXML
	public void OnDireccion(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDireccion(event.getNewValue());
    }
	/**
	 * Método para manejar el evento de edición de la columna de telefono.
	 * @param event El evento de edición de la celda.
	 */
	@FXML
	public void OnTelefono(TableColumn.CellEditEvent<Cliente, String> event) {
        Cliente current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setTelefono(event.getNewValue());
    }
	/**
	 * Método para cargar los datos de los clientes desde la base de datos y mostrarlos en la tabla.
	 * @param event El evento del mouse que desencadena la carga de datos. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void cargarBtn(MouseEvent event) {
        LinkedList<Cliente> data1 = data.getDatos();
        Datos.setAll(data1);
    }
	/**
	 * Método para crear un nuevo cliente en la base de datos y agregarlo a la lista de clientes.
	 * @param event El evento del mouse que desencadena la creación del cliente. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void crearBtn(MouseEvent event) {
	    String cedula = TextCedula.getText();
	    if (data.existeCliente(cedula)) {
	        System.out.println("El cliente con la cédula " + cedula + " ya existe en la base de datos.");
	        Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("corrija los datos del cliente e intente de nuevo");
            error.setContentText("El cliente con la cédula " + cedula + " ya existe en la base de datos.");
            error.setTitle("Error al crear el cliente");
            error.show();
	    } else {
	        Cliente nuevoCliente = new Cliente(
	            cedula,
	            TextNombre.getText(),
	            TextDireccion.getText(),
	            TextTelefono.getText()
	        );
	        data.guardarCliente(nuevoCliente);
	        Datos.add(nuevoCliente);
	        limpiarCamposTexto();
	    }
	}
	/**
	 * Método para eliminar los datos de un cliente en la base de datos y de la tabla.
	 * @param event El evento del mouse que desencadena la eliminación del cliente. Puede ser nulo si se llama al método desde otro lugar.
	 */
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
	/**
	 * Método para actualizar un cliente en la base de datos y actualizarlo en la lista de clientes.
	 * @param event El evento del mouse que desencadena la actualización del cliente. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void actualizarBtn(MouseEvent event) {
        Cliente seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setCedula(TextCedula.getText());
            seleccion.setNombre(TextNombre.getText());
            seleccion.setDireccion(TextDireccion.getText());
            seleccion.setTelefono(TextTelefono.getText());

            data.actualizarCliente(seleccion);
            limpiarCamposTexto();
            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
        } else {
            System.out.println("Error: No se ha seleccionado ningún cliente para actualizar.");
        }
    }
    
	/**
	 * Método privado para limpiar los campos de texto en la interfaz gráfica.
	 */
    private void limpiarCamposTexto() {
        TextCedula.clear();
        TextNombre.clear();
        //TextApellido.clear();
        TextDireccion.clear();
        TextTelefono.clear();
       }
    
	/**
	 * Método para manejar el evento de cierre de sesión.
	 * Este método cierra la sesión actual del usuario, mostrando la ventana de inicio de sesión (Login) y cerrando la ventana actual.
	 * @param event El evento de clic del mouse.
	 * @throws IOException
	 */
	@FXML
	public void btnCerrar(MouseEvent event) throws IOException {
		System.out.println("cerrar secion");
        Stage currenStage = (Stage) btnCerrar.getScene().getWindow();
		//currenStage.close();
		//abrir ventana
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Menu.fxml"));
		//Stage stage = new Stage();
		currenStage.setScene(new Scene(loader.load()));
		currenStage.show();
        return;
	}
    
}