package application;


/**
 * Importa las clases necesarias.
 * @author Kevin Santiago
 */
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Esta clase sirve para ver y manipular las acciones de la ventana usuario.
 * @author Kevin Santiago
 */
public class UsuarioController implements Initializable {
    @FXML
    private TableView<Usuario> Tablaview;
    @FXML
    private TableColumn<Usuario, String> CedUsuarioColumn;
    @FXML
    private TableColumn<Usuario, String> NombreColumn;
    @FXML
    private TableColumn<Usuario, String> DireccionColumn;
    @FXML
    private TableColumn<Usuario, String> TelefonoColumn;
    @FXML
    private TableColumn<Usuario, String> UsuarioColumn;
    @FXML
    private TableColumn<Usuario, String> ContrasenaColumn;
    @FXML
    private TableColumn<Usuario, String> CargoColumn;
    @FXML
    private TextField TextCedUsuario;
    @FXML
    private TextField TextNombre;
    @FXML
    private TextField TextDireccion;
    @FXML
    private TextField TextTelefono;
    @FXML
    private TextField TextUsuario;
    @FXML
    private TextField TextContrasena;
    @FXML
    private TextField TextCargo;
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
     * Declaración y creación de una lista observable de objetos Usuario
     */
    ObservableList<Usuario> Datos = FXCollections.observableArrayList();
    DatosUsuario data;

    /**
     * Método de inicialización del controlador
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data = new DatosUsuario();
        this.CedUsuarioColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Cedula"));
        this.NombreColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Nombre"));
        this.DireccionColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Direccion"));
        this.TelefonoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Telefono"));
        this.UsuarioColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Usuario"));
        this.ContrasenaColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Contrasena"));
        this.CargoColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Cargo"));

        Tablaview.setItems(this.Datos);
        this.CedUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DireccionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.TelefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.UsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ContrasenaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.CargoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    /**
	* Metodo que si selecciona un usuario, actualiza los campos de texto con sus datos.
	*/
    @FXML
    public void Tablaview(MouseEvent event) {
        Usuario seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextCedUsuario.setText(seleccion.getCedula());
            this.TextNombre.setText(seleccion.getNombre());
            this.TextDireccion.setText(seleccion.getDireccion());
            this.TextTelefono.setText(seleccion.getTelefono());
            this.TextUsuario.setText(seleccion.getUsuario());
            this.TextContrasena.setText(seleccion.getContrasena());
            this.TextCargo.setText(seleccion.getCargo());
        }
    }

    /**
     * Método para manejar el evento de edición de la columna de cédula
     * @param event
     */
    @FXML
    public void OnCedUsuarioColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCedula(event.getNewValue());
    }

    /**
     * Método para manejar el evento de edición de la columna de nombre
     * @param event
     */
    @FXML
    public void OnNombreColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setNombre(event.getNewValue());
    }

    /**
     * Método para manejar el evento de edición de la columna de dirección.
     * @param event
     */
    @FXML
    public void OnDireccionColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDireccion(event.getNewValue());
    }

    /**
     * Método para manejar el evento de edición de la columna de telefono.
     * @param event
     */
    @FXML
    public void OnTelefonoColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setTelefono(event.getNewValue());
    }

    /**
     * Método para manejar el evento de edición de la columna de usuario
     * @param event
     */
   @FXML
    public void OnUsuarioColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setUsuario(event.getNewValue());
    }

   /**
    * Método para manejar el evento de edición de la columna de contraseña.
    * @param event
    */
    @FXML
    public void OnContrasenaColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setContrasena(event.getNewValue());
    }

    /**
     * Método para manejar el evento de edición de la columna de cargo
     * @param event
     */
    @FXML
    public void OnCargoColumn(TableColumn.CellEditEvent<Usuario, String> event) {
        Usuario current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCargo(event.getNewValue());
    }

    /**
     * Metodo para cargar los usuarios existentes en la base de datos.
     * @param event
     */
    @FXML
    public void cargarBtn(MouseEvent event) {
        LinkedList<Usuario> data1 = data.getDatos();
        Datos.setAll(data1);
    }
    
    /**
     * Metodo para crear un nuevo usuario en la base de datos.
     * @param event
     */
    @FXML
    public void crearBtn(MouseEvent event) {
        String cedulaUsuario = TextCedUsuario.getText();
        if (data.existeUsuario(cedulaUsuario)) {
            System.out.println("El usuario con la cédula " + cedulaUsuario + " ya existe en la base de datos.");
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("corrija los datos del usuario e intente de nuevo");
            error.setContentText("El usuario con la cédula " + cedulaUsuario + " ya existe en la base de datos.");
            error.setTitle("Error al crear el usuario");
            error.show();
            
        } else {
            Usuario nuevoUsuario = new Usuario(
                cedulaUsuario,
                TextNombre.getText(),
                TextDireccion.getText(),
                TextTelefono.getText(),
                TextUsuario.getText(),
                TextContrasena.getText(),
                TextCargo.getText()
            );
            data.guardarUsuario(nuevoUsuario);
            Datos.add(nuevoUsuario);
            limpiarCamposTexto();
        }
    }
    
    /**
     * Metodo para eliminar un usuario de la base de datos.
     * @param event
     */
    @FXML
    public void eliminarBtn(MouseEvent event) {
        Usuario seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            data.eliminarUsuario(seleccion.getCedula());
            Datos.remove(seleccion);
            limpiarCamposTexto();
        } else {
            System.out.println("Error: No se ha seleccionado ningún usuario para eliminar.");
        }
    }
    
    /**
     * Metodo para actualizar los datos de los usuario en la base de datos.
     * @param event
     */
    @FXML
    public void actualizarBtn(MouseEvent event) {
        Usuario seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setCedula(TextCedUsuario.getText());
            seleccion.setNombre(TextNombre.getText());
            seleccion.setDireccion(TextDireccion.getText());
            seleccion.setTelefono(TextTelefono.getText());
            seleccion.setUsuario(TextUsuario.getText());
            seleccion.setContrasena(TextContrasena.getText());
            seleccion.setCargo(TextCargo.getText());

            data.actualizarUsuario(seleccion);
            limpiarCamposTexto();
            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
        } else {
            System.out.println("Error: No se ha seleccionado ningún usuario para actualizar.");
        }
    }
    
    /**
     * Método auxiliar para limpiar los campos de texto
     */
    private void limpiarCamposTexto() {
        TextCedUsuario.clear();
        TextNombre.clear();
        TextDireccion.clear();
        TextTelefono.clear();
        TextUsuario.clear();
        TextContrasena.clear();
        TextCargo.clear();
    }

    /**
     * Método que permite cerrar la ventana usuario y retornar a la ventana Menu
     * @param event
     * @throws IOException
     */
    @FXML
    public void btnCerrar(MouseEvent event) throws IOException {
        System.out.println("cerrar sesion");

        Stage currenStage = (Stage) btnCerrar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        currenStage.setScene(new Scene(loader.load()));
        currenStage.show();
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

public class ProductoController implements Initializable{
	@FXML
	private TableView<Producto> Tablaview;
	@FXML
	private TableColumn<Usuario, String> ReferenciaColumn;
	@FXML
	private TableColumn<Producto, String> DescripcionColumn;
	@FXML
	private TableColumn<Producto, String> CategoriaColumn;
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
	private TextField TextCategoria;
	@FXML
	private TextField TextStock;
	@FXML
	private TextField TextValor;

	
	ObservableList<Producto> Datos = FXCollections.observableArrayList();
    
    DatosProducto data;
	// Event Listener on TableView[#Tablaview].onMouseClicked
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.data= new DatosProducto();
        this.ReferenciaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Referencia"));
        this.DescripcionColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Descripcion"));
        this.CategoriaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Categoria"));
        this.StockColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Stock"));
        this.ValorColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Valor"));
        
        Tablaview.setItems(this.Datos);
        this.ReferenciaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.DescripcionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.CategoriaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.StockColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ValorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                       
    }
    
    @FXML
    public void Tablaview(MouseEvent event) {
        Producto seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            this.TextReferencia.setText(seleccion.getReferencia());
            this.TextDescripcion.setText(seleccion.getDescripcion());
            this.TextCategoria.setText(seleccion.getCategoria());
            this.TextStock.setText(seleccion.getStock());
            this.TextValor.setText(seleccion.getValor());
            
           

        }
    }
	// Event Listener on TableColumn[#ReferenciaColumn].onEditCommit
	@FXML
	public void OnReferencia(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setReferencia(event.getNewValue());
    }
	// Event Listener on TableColumn[#DescripcionColumn].onEditCommit
	@FXML
	public void OnDescripcionColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setDescripcion(event.getNewValue());
    }
	// Event Listener on TableColumn[#CategoriaColumn].onEditCommit
	@FXML
	public void OnCategoriaColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setCategoria(event.getNewValue());
    }
	// Event Listener on TableColumn[#StockColumn].onEditCommit
	@FXML
	public void OnStockColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setStock(event.getNewValue());
    }
	// Event Listener on TableColumn[#ValorColumn].onEditCommit
	@FXML
	public void OnValorColumn(TableColumn.CellEditEvent<Producto, String> event) {
        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
        current.setValor(event.getNewValue());
    }
	// Event Listener on Button[#cargarBtn].onMouseClicked
	@FXML
	public void cargarBtn(MouseEvent event) {
		LinkedList<Producto> data1 = data.getDatos();
        Datos.setAll(data1);
    }
	// Event Listener on Button[#crearBtn].onMouseClicked
	@FXML
	public void crearBtn(MouseEvent event) {
		Producto nuevoProducto = new Producto(
				TextReferencia.getText(),
				TextDescripcion.getText(),
				TextCategoria.getText(),
				TextStock.getText(),
				TextValor.getText()
	        );
	        data.guardarProducto(nuevoProducto);
	        Datos.add(nuevoProducto);
	        limpiarCamposTexto();
	    }
	// Event Listener on Button[#eliminarBtn].onMouseClicked
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
	// Event Listener on Button[#actualizarBtn].onMouseClicked
	@FXML
	public void actualizarBtn(MouseEvent event) {
		Producto seleccion = Tablaview.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            seleccion.setReferencia(TextReferencia.getText());
            seleccion.setDescripcion(TextDescripcion.getText());
            seleccion.setCategoria(TextCategoria.getText());
            seleccion.setStock(TextStock.getText());
            seleccion.setValor(TextValor.getText());

            data.actualizarProducto(seleccion);
            limpiarCamposTexto();
            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
        } else {
            System.out.println("Error: No se ha seleccionado ningún cliente para actualizar.");
        }
    }
	private void limpiarCamposTexto() {
		TextReferencia.clear();
		TextDescripcion.clear();
		TextCategoria.clear();
		TextStock.clear();
		TextValor.clear();
       }
}
*/

