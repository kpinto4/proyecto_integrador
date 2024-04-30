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

public class vista5Controller {
	@FXML
	private TableView<Producto> Tablaview;
	@FXML
	private TableColumn<Producto, String> ReferenciaColumn;
	@FXML
	private TableColumn<Producto, String> DescripcionColumn;
	@FXML
	private TableColumn<Producto, String> CategoriaColumn;
	@FXML
	private TableColumn<Producto, String> CantidadColumn;
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
	private TextField TextReferencia;
	@FXML
	private TextField TextValor;
	@FXML
	private TextField TextDescripcion;
	@FXML
	private TextField TextStock;
	@FXML
	private TextField TextCategoria;
	@FXML
	private TextField TextCantidad;
	
	ObservableList<Producto> Datos = FXCollections.observableArrayList();
	
	DatosProducto data;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.data= new DatosProducto();
		this.ReferenciaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Referencia"));
		this.DescripcionColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Descripcion"));
		this.CategoriaColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Categoria"));
		this.CantidadColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Cantidad"));
		this.ValorColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Valor"));
		this.StockColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("Stock"));
		
		
		
		Tablaview.setItems(this.Datos);
		this.ReferenciaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.DescripcionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.CategoriaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.CantidadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.ValorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		this.StockColumn.setCellFactory(TextFieldTableCell.forTableColumn());
				
	}

	// Event Listener on TableView[#Tablaview].onMouseClicked
	@FXML
	public void Tablaview(MouseEvent event) {
		// TODO Autogenerated
		Producto seleccion = this.Tablaview.getSelectionModel().getSelectedItem();
		this.TextReferencia.setText(seleccion.getReferencia());
		this.TextValor.setText(seleccion.getValor());
		this.TextDescripcion.setText(seleccion.getDescripcion());
		this.TextStock.setText(seleccion.getStock());
		this.TextCategoria.setText(seleccion.getCategoria());
		this.TextCantidad.setText(seleccion.getCantidad());
	}
	// Event Listener on TableColumn[#ReferenciaColumn].onEditCommit
	@FXML
	public void OnReferenciaColumn(TableColumn.CellEditEvent<Producto, String> event) {
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
	// Event Listener on TableColumn[#CantidadColumn].onEditCommit
	@FXML
	public void OnCantidadColumn(TableColumn.CellEditEvent<Producto, String> event) {
		Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
		current.setCantidad(event.getNewValue());
	}
	// Event Listener on TableColumn[#ValorColumn].onEditCommit
	@FXML
	public void OnValorColumn(TableColumn.CellEditEvent<Producto, String> event) {
		Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
		current.setValor(event.getNewValue());
	}
	// Event Listener on TableColumn[#StockColumn].onEditCommit
	@FXML
	public void OnStockColumn(TableColumn.CellEditEvent<Producto, String> event) {
		Producto current = this.Tablaview.getSelectionModel().getSelectedItem();
		current.setStock(event.getNewValue());
	}
	// Event Listener on Button[#cargarBtn].onMouseClicked
	@FXML
	public void cargarBtn(MouseEvent event) {
		LinkedList<Producto> data1 = data.getDatos();
		for(Producto a : data1) {
			this.Datos.add(a);
		}
	}
	// Event Listener on Button[#enviarBtn].onMouseClicked
	@FXML
	public void enviarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#eliminarBtn].onMouseClicked
	@FXML
	public void eliminarBtn(MouseEvent event) {
		// TODO Autogenerated
	}
}
