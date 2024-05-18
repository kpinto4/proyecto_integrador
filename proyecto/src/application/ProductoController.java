package application;

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

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ProductoController implements Initializable{
	@FXML
	private TableView<Producto> Tablaview;
	@FXML
	private TableColumn<Producto, String> ReferenciaColumn;
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
	@FXML
	private ImageView btnCerrar;
		

	
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
	
	
	

	// Event Listener on Button[#btnCerrar].onMouseClicked
	@FXML
	public void btnCerrar(MouseEvent event) throws IOException {
		// TODO Autogenerated
		
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