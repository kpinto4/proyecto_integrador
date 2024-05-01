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



public class vista5Controller implements Initializable{
	 @FXML
	    private TableView<Producto> Tablaview;
	    @FXML
	    private TableColumn<Producto, String> CodProductoColumn;
	    @FXML
	    private TableColumn<Producto, String> DescripcionColumn;
	    @FXML
	    private TableColumn<Producto, String> CategoriaColumn;
	    @FXML
	    private TableColumn<Producto, String> ValorColumn;
	    @FXML
	    private TableColumn<Producto, String> StockColumn;
	    @FXML
	    private Button cargarBtn;
	    @FXML
	    private Button enviarBtn;
	    @FXML
	    private Button eliminarBtn;
	    @FXML
	    private TextField TextCodProducto;
	    @FXML
	    private TextField TextValor;
	    @FXML
	    private TextField TextDescripcion;
	    @FXML
	    private TextField TextStock;
	    @FXML
	    private TextField TextCategoria;

	    ObservableList<Producto> Datos = FXCollections.observableArrayList();
	    
	    DatosProducto data;

	    public void initialize(URL arg0, ResourceBundle arg1) {
	        this.data= new DatosProducto();
	        this.CodProductoColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("CodProducto"));
	        this.DescripcionColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Descripcion"));
	        this.CategoriaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Categoria"));
	        this.ValorColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Valor"));
	        this.StockColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Stock"));
	        
	        Tablaview.setItems(this.Datos);
	        this.CodProductoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        this.DescripcionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        this.CategoriaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        this.ValorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        this.StockColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	                
	    }
	    
	    @FXML
	    public void Tablaview(MouseEvent event) {
	        Producto seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
	        if (seleccion != null) {
	            this.TextCodProducto.setText(seleccion.getCodProducto());
	            this.TextValor.setText(seleccion.getValor());
	            this.TextDescripcion.setText(seleccion.getDescripcion());
	            this.TextStock.setText(seleccion.getStock());
	            this.TextCategoria.setText(seleccion.getCategoria());
	        }
	    }

	    @FXML
	    public void OnCodProducto(TableColumn.CellEditEvent<Producto, String> event) {
	        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
	        current.setCodProducto(event.getNewValue());
	    }

	    @FXML
	    public void OnDescripcionColumn(TableColumn.CellEditEvent<Producto, String> event) {
	        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
	        current.setDescripcion(event.getNewValue());
	    }

	    @FXML
	    public void OnCategoriaColumn(TableColumn.CellEditEvent<Producto, String> event) {
	        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
	        current.setCategoria(event.getNewValue());
	    }

	    @FXML
	    public void OnValorColumn(TableColumn.CellEditEvent<Producto, String> event) {
	        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
	        current.setValor(event.getNewValue());
	    }

	    @FXML
	    public void OnStockColumn(TableColumn.CellEditEvent<Producto, String> event) {
	        Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
	        current.setStock(event.getNewValue());
	    }

	    @FXML
	    public void cargarBtn(MouseEvent event) {
	        LinkedList<Producto> data1 = data.getDatos();
	        Datos.setAll(data1);
	    }

	    @FXML
	    public void crearBtn(MouseEvent event) {
	        Producto nuevoProducto = new Producto(
	            TextCodProducto.getText(),
	            TextDescripcion.getText(),
	            TextCategoria.getText(),
	            TextStock.getText(),
	            TextValor.getText()
	        );
	        data.guardarProducto(nuevoProducto);
	        Datos.add(nuevoProducto);
	        limpiarCamposTexto();
	    }

	    @FXML
	    public void eliminarBtn(MouseEvent event) {
	        Producto seleccion = Tablaview.getSelectionModel().getSelectedItem();
	        if (seleccion != null) {
	            data.eliminarProducto(seleccion.getCodProducto());
	            Datos.remove(seleccion);
	            limpiarCamposTexto();
	        } else {
	            System.out.println("Error: No se ha seleccionado ningún producto para eliminar.");
	        }
	    }

	 
	    @FXML
	    public void actualizarBtn(MouseEvent event) {
	        Producto seleccion = Tablaview.getSelectionModel().getSelectedItem();
	        if (seleccion != null) {
	            seleccion.setCodProducto(TextCodProducto.getText());
	            seleccion.setDescripcion(TextDescripcion.getText());
	            seleccion.setCategoria(TextCategoria.getText());
	            seleccion.setStock(TextStock.getText());
	            seleccion.setValor(TextValor.getText());

	            data.actualizarProducto(seleccion);
	            limpiarCamposTexto();
	            cargarBtn(null); // Llama al método cargarBtn para actualizar la tabla
	        } else {
	            System.out.println("Error: No se ha seleccionado ningún producto para actualizar.");
	        }
	    }
	    
	    private void limpiarCamposTexto() {
	        TextCodProducto.clear();
	        TextDescripcion.clear();
	        TextCategoria.clear();
	        TextStock.clear();
	        TextValor.clear();
	    }
	}