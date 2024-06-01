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
 * Esta clase sirve para ver y manipular las acciones de la ventana producto.
 * @author Kevin Santiago
 *
 */
public class ProductoController implements Initializable{
	@FXML
	private TableView<Producto> Tablaview;
	@FXML
	private TableColumn<Producto, String> ReferenciaColumn;
	@FXML
	private TableColumn<Producto, String> DescripcionColumn;
	@FXML
	private TableColumn<Producto, String> Categoria_idColumn;
	@FXML
	private TableColumn<Producto, String> StockColumn;
	@FXML
	private TableColumn<Producto, String> ValorColumn;
	@FXML
	private Button cargarBtn;
	@FXML
	private Button crearBtn;
	@FXML
	private Button eliminarBtn;
	@FXML
	private Button actualizarBtn;
	@FXML
	private TextField TextReferencia;
	@FXML
	private TextField TextDescripcion;
	@FXML
	private TextField TextCategoria_id;
	@FXML
	private TextField TextStock;
	@FXML
	private TextField TextValor;
	@FXML
	private ImageView btnCerrar;
		

	/**
	 * Declaración y creación de una lista observable de objetos Producto.
	 */
	ObservableList<Producto> Datos = FXCollections.observableArrayList();
    DatosProducto data;
    
	/**
	 * Método de inicialización del controlador.
	 */
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data= new DatosProducto();
        this.ReferenciaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Referencia"));
        this.DescripcionColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Descripcion"));
        this.Categoria_idColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Categoria_id"));
        this.StockColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Stock"));
        this.ValorColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Valor"));
        
        Tablaview.setItems(this.Datos);
        this.ReferenciaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DescripcionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Categoria_idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.StockColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ValorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                       
    }
    
    /**
     * Método para manejar el evento de selección de un elemento en la tabla de productos.
     * Si se selecciona un producto en la tabla, actualiza los campos de texto con los datos del producto seleccionado. 
     * @param event El evento de ratón que desencadena la selección de un elemento en la tabla.
     */
    @FXML
    public void Tablaview(MouseEvent event) {
        Producto seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextReferencia.setText(seleccion.getReferencia());
            this.TextDescripcion.setText(seleccion.getDescripcion());
            this.TextCategoria_id.setText(seleccion.getCategoria_id());
            this.TextStock.setText(seleccion.getStock());
            this.TextValor.setText(seleccion.getValor());
        }
    }
	/**
	  * Método para manejar el evento de edición de la columna "Referencia" en la tabla de productos.
	  * Actualiza la referencia del producto seleccionado con el nuevo valor introducido.
	 * @param event El evento de edición que contiene el nuevo valor introducido.
	 */
	@FXML
	public void OnReferencia(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setReferencia(event.getNewValue());
    }
	/**
	  * Método para manejar el evento de edición de la columna "Descripcion" en la tabla de productos.
	  * Actualiza la descripcion del producto seleccionado con la nueva información introducida.
	 * @param event El evento de edición que contiene el nuevo dato introducido.
	 */
	@FXML
	public void OnDescripcionColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDescripcion(event.getNewValue());
    }
	/**
	  * Método para manejar el evento de edición de la columna "Categoria" en la tabla de productos.
	  * Actualiza la categoria del producto seleccionado con el nuevo valor introducido.
	 * @param event El evento de edición que contiene el nuevo valor introducido.
	 */
	@FXML
	public void OnCategoria_idColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCategoria_id(event.getNewValue());
    }
	/**
	  * Método para manejar el evento de edición de la columna "Stock" en la tabla de productos.
	  * Actualiza la stock del producto seleccionado con el nuevo valor introducido.
	 * @param event El evento de edición que contiene el nuevo valor introducido.
	 */
	@FXML
	public void OnStockColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setStock(event.getNewValue());
    }
	/**
	  * Método para manejar el evento de edición de la columna "Valor" en la tabla de productos.
	  * Actualiza la valor del producto seleccionado con el nuevo valor introducido.
	 * @param event El evento de edición que contiene el nuevo valor introducido.
	 */
	@FXML
	public void OnValorColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setValor(event.getNewValue());
    }
	/**
	 * Método para cargar los datos de los productos desde la base de datos y mostrarlos en la tabla.
	 * Obtiene los datos de los productos utilizando el objeto de la clase DatosProducto y los establece en la lista observable "Datos". 
	 * @param event El evento del mouse que desencadena la carga de datos. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void cargarBtn(MouseEvent event) {
		LinkedList<Producto> data1 = data.getDatos();
        Datos.setAll(data1);
    }
	/**
	 * Método para crear un nuevo producto en la base de datos y agregarlo a la lista de productos.
	 * @param event El evento del mouse que desencadena la creación del producto. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void crearBtn(MouseEvent event) {
	    String referencia = TextReferencia.getText();
	    if (data.existeProducto(referencia)) {
	        System.out.println("El producto con la referencia " + referencia + " ya existe en la base de datos.");
	        Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("corrija los datos del producto e intente de nuevo");
            error.setContentText("El producto con la referencia " + referencia + " ya existe en la base de datos.");
            error.setTitle("Error al crear el producto");
            error.show();
	    } else {
	        Producto nuevoProducto = new Producto(
	            referencia,
	            TextDescripcion.getText(),
	            TextCategoria_id.getText(),
	            TextStock.getText(),
	            TextValor.getText()
	        );
	        data.guardarProducto(nuevoProducto);
	        Datos.add(nuevoProducto);
	        limpiarCamposTexto();
	    }
	}
	/**
	 * Método para eliminar un producto seleccionado de la base de datos y de la lista de productos mostrada en la tabla.
	 * @param event El evento del mouse que desencadena la eliminación del producto. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void eliminarBtn(MouseEvent event) {
		Producto seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            data.eliminarProducto(seleccion.getReferencia());
            Datos.remove(seleccion);
            limpiarCamposTexto();
        } else {
            System.out.println("Error: No se ha seleccionado ningún cliente para eliminar.");
        }
    }
	/**
	 * Método para actualizar un producto seleccionado de la base de datos y de la lista de productos mostrada en la tabla.
	 * @param event El evento del mouse que desencadena la actualización del producto. Puede ser nulo si se llama al método desde otro lugar.
	 */
	@FXML
	public void actualizarBtn(MouseEvent event) {
		Producto seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setReferencia(TextReferencia.getText());
            seleccion.setDescripcion(TextDescripcion.getText());
            seleccion.setCategoria_id(TextCategoria_id.getText());
            seleccion.setStock(TextStock.getText());
            seleccion.setValor(TextValor.getText());

            data.actualizarProducto(seleccion);
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
		TextReferencia.clear();
		TextDescripcion.clear();
		TextCategoria_id.clear();
		TextStock.clear();
		TextValor.clear();
       }
	
	/**
	 * Método para manejar el evento de cierre de sesión.
	 * @param event El evento de clic del mouse.
	 * @throws IOException
	 */
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
		
		
			
		
		
		/*Stage currentStage = (Stage) btnCerrar.getScene().getWindow();
        //currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        //Stage stage = new Stage();
        currentStage.setScene(new Scene(loader.load()));
        currentStage.show();*/
		
		
        

	}


	
}

/*
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
*/