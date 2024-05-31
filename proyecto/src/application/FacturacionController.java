package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class FacturacionController implements Initializable {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "proto";
    private static final String PASSWORD = "proto";
	
    @FXML
    private MenuButton FormaDePagoMenu;

    @FXML
    private MenuItem Efectivobtn;

    @FXML
    private MenuItem TCreditobtn;

    @FXML
    private MenuItem TDebitoBtn;

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
    private TableView<Producto> Tablaview;

    @FXML
    private TableColumn<Producto, String> ReferenciaColumn;

    @FXML
    private TableColumn<Producto, String> DescripcionColumn;

    @FXML
    private TableColumn<Producto, String> CantidadColumn;

    @FXML
    private TableColumn<Producto, String> ValorUColumn;

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
    private Button agregarBtn;

    private DatosFactura datosFactura = DatosFactura.getInstance();

    private ProductoFac productoFac;
    
    private int numeroFactura = 0; // Inicializamos el contador en 0 al principio

    private int metodoPagoSeleccionado = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar ProductoFac
        Connection conexion = datosFactura.getConnection();
        productoFac = new ProductoFac(conexion);
        
        configurarEventosMenuItem();
    

        // Asignar las propiedades de Producto a las columnas de la tabla
        ReferenciaColumn.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        DescripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        CantidadColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ValorUColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
     // Agregar evento de tecla presionada al campo de texto cedulaClienteText
        cedulaClienteText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buscarClientePorCedula();
            }
        });
        
     // Obtener el último número de factura desde la base de datos (si es necesario)
        numeroFactura = obtenerUltimoNumeroFacturaDesdeBD();

        // Actualizar el campo de texto con el número de factura
        numeroFcaturaText.setText(String.valueOf(numeroFactura));
        
        
        
    
        
     /*// Crear instancias de MenuItem para representar las opciones de método de pago
        MenuItem efectivoMenuItem = new MenuItem("Efectivo");
        MenuItem tCreditoMenuItem = new MenuItem("Tarjeta de Crédito");
        MenuItem tDebitoMenuItem = new MenuItem("Tarjeta de Débito");

        // Configurar eventos para cada MenuItem
        efectivoMenuItem.setOnAction(event -> {
            metodoPagoSeleccionado = 1;
            FormaDePagoMenu.setText("Efectivo");
        });

        tCreditoMenuItem.setOnAction(event -> {
            metodoPagoSeleccionado = 2;
            FormaDePagoMenu.setText("Tarjeta de Crédito");
        });

        tDebitoMenuItem.setOnAction(event -> {
            metodoPagoSeleccionado = 3;
            FormaDePagoMenu.setText("Tarjeta de Débito");
        });

        // Agregar los MenuItem a tu lista de MenuItem
        List<MenuItem> listaMenuItems = new ArrayList<>();
        listaMenuItems.add(efectivoMenuItem);
        listaMenuItems.add(tCreditoMenuItem);
        listaMenuItems.add(tDebitoMenuItem);
    }*/
    }
    
    private void configurarEventosMenuItem() {
        Efectivobtn.setOnAction(event -> {
            metodoPagoSeleccionado = 1;
            FormaDePagoMenu.setText("Efectivo");
        });

        TCreditobtn.setOnAction(event -> {
            metodoPagoSeleccionado = 2;
            FormaDePagoMenu.setText("Tarjeta de Crédito");
        });

        TDebitoBtn.setOnAction(event -> {
            metodoPagoSeleccionado = 3;
            FormaDePagoMenu.setText("Tarjeta de Débito");
        });
    }

    @FXML
    public void buscarClientePorCedula() {
        String cedula = cedulaClienteText.getText();
        if (!cedula.isEmpty()) {
            Cliente cliente = datosFactura.buscarClientePorCedula(cedula);
            if (cliente != null) {
                nombreClienteText.setText(cliente.getNombre());
            } else {
                System.out.println("No se encontró ningún cliente con la cédula proporcionada.");
                nombreClienteText.clear(); // Limpiamos el campo de texto si no se encontró ningún cliente
            }
        } else {
            System.out.println("Por favor, ingrese una cédula antes de buscar.");
            nombreClienteText.clear(); // Limpiamos el campo de texto si no se ingresó una cédula
        }
    }

    @FXML
    public void agregarProductoFactura(MouseEvent event) {
        // Obtener la referencia del producto y la cantidad
        String referencia = referenciaText.getText();
        int cantidad = Integer.parseInt(cantidadText.getText()); // Obtener la cantidad del TextField

        // Verificar si el producto existe y hay suficiente stock
        Producto producto = null;
        try {
            producto = productoFac.buscarProductoPorReferencia(referencia);
            if (producto != null && Integer.parseInt(producto.getStock()) >= cantidad) {
                // Modificar la cantidad del producto con la cantidad ingresada
                producto.setStock(String.valueOf(cantidad));

                // Agregar el producto a la tabla
                Tablaview.getItems().add(producto);

                // Actualizar el total a pagar
                actualizarTotalAPagar();
            } else {
                // Mostrar mensaje de error
                System.out.println("Error: Producto no encontrado o stock insuficiente.");
            }
        } catch (SQLException e) {
            // Manejar la excepción SQL
            e.printStackTrace();
            System.out.println("Error al buscar producto por referencia: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Manejar la excepción de formato incorrecto para la cantidad
            System.out.println("Error: La cantidad debe ser un número entero válido.");
        }
    }

    private void actualizarTotalAPagar() {
        double total = 0.0;
        ObservableList<Producto> productos = Tablaview.getItems();

        for (Producto producto : productos) {
            total += Double.parseDouble(producto.getValor()) * Integer.parseInt(producto.getStock());
        }

        totalPagoText.setText(String.valueOf(total));
    }




    @FXML
    public void ReferenciaColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    @FXML
    public void DescripcionColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    @FXML
    public void CantidadColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    @FXML
    public void ValorUColumn(ActionEvent event) {
        // TODO Autogenerated
    }

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
		}

    @FXML
    public void btnBuscar() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Consultar todas las facturas
            String sql = "SELECT * FROM factura";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Obtener los detalles de cada factura
                int facturaId = resultSet.getInt("factura_id");
                Date fecha = resultSet.getDate("fecha");
                String cedulaCliente = resultSet.getString("cedula_cliente");
                int metodoPagoId = resultSet.getInt("metodo_pago_id");

                // Imprimir o procesar los datos de la factura
                System.out.println("Factura ID: " + facturaId);
                System.out.println("Fecha: " + fecha);
                System.out.println("Cedula Cliente: " + cedulaCliente);
                System.out.println("Metodo de Pago ID: " + metodoPagoId);

                // Obtener los detalles de la factura
                List<DetalleFactura> detalles = obtenerDetallesFactura(facturaId);
                for (DetalleFactura detalle : detalles) {
                    // Imprimir o procesar los detalles de la factura
                    System.out.println("Detalle ID: " + detalle.getDetalleId());
                    System.out.println("Referencia Producto: " + detalle.getReferenciaProducto());
                    System.out.println("Descripcion: " + detalle.getDescripcion());
                    System.out.println("Cantidad: " + detalle.getCantidad());
                    System.out.println("Valor Total: " + detalle.getValorTotal());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<DetalleFactura> obtenerDetallesFactura(int facturaId) {
        List<DetalleFactura> detalles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM detalle_factura WHERE factura_id = ?")) {
            
            // Establecer el parámetro para la consulta preparada
            statement.setInt(1, facturaId);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            
            // Iterar sobre los resultados y agregar cada detalle a la lista
            while (resultSet.next()) {
                int detalleId = resultSet.getInt("detalle_id");
                int referenciaProducto = resultSet.getInt("referencia_producto");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double valorTotal = resultSet.getDouble("valor_total");
                
                // Crear un objeto DetalleFactura y agregarlo a la lista
                DetalleFactura detalle = new DetalleFactura(detalleId, facturaId, referenciaProducto, descripcion, cantidad, valorTotal);
                detalles.add(detalle);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

	// Método para imprimir los detalles de la factura en la consola
    public void imprimirDetallesFactura(Factura factura) {
        // Mostrar información básica de la factura
    	System.out.println("mostrar factrua");

    }


    @FXML
    public void generarFactura(MouseEvent event) {
        System.out.println("Generar y guardar factura");

        // Obtener el último número de factura desde la base de datos y generar un nuevo número único
        int ultimoNumeroFactura = obtenerUltimoNumeroFacturaDesdeBD();
        int numeroFactura = ultimoNumeroFactura + 1;

        // Obtener otros datos de la factura
        Date fecha = new Date(System.currentTimeMillis()); // Obtener la fecha actual
        String cedulaCliente = cedulaClienteText.getText();
        int metodoPagoId = obtenerMetodoPagoSeleccionado();
        double totalPago = Double.parseDouble(totalPagoText.getText());

        // Obtener productos de la tabla
        ObservableList<Producto> productos = Tablaview.getItems();
        List<DetalleFactura> detalles = new ArrayList<>();

        for (Producto producto : productos) {
            DetalleFactura detalle = new DetalleFactura(
                    obtenerNuevoDetalleId(), // Implementar lógica para obtener nuevo ID de detalle
                    numeroFactura,
                    Integer.parseInt(producto.getReferencia()),
                    producto.getDescripcion(),
                    Integer.parseInt(producto.getStock()),
                    Double.parseDouble(producto.getValor()) * Integer.parseInt(producto.getStock())
            );
            detalles.add(detalle);
        }

        // Crear la factura
        Factura factura = new Factura(numeroFactura, fecha, cedulaCliente, metodoPagoId, detalles);

        // Guardar la factura en la base de datos
        DatosFactura datosFactura = DatosFactura.getInstance();
        datosFactura.generarFactura(factura);
        System.out.println("Factura generada y guardada con éxito.");
    }
    
    private int obtenerMetodoPagoSeleccionado() {
        // Devolver el método de pago seleccionado
        return metodoPagoSeleccionado;
    }
    
    /*private void obtenerMetodosDePagoDesdeBD() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT METODO_ID, NOMBRE FROM metodo_pago";
            ResultSet resultSet = statement.executeQuery(sql);

            // Limpiar cualquier contenido previo en el menú desplegable
            FormaDePagoMenu.getItems().clear();

            // Agregar métodos de pago al menú desplegable
            while (resultSet.next()) {
                int metodoPagoId = resultSet.getInt("METODO_ID");
                String nombreMetodoPago = resultSet.getString("NOMBRE");

                MenuItem menuItem = new MenuItem(nombreMetodoPago);
                menuItem.setOnAction(event -> {
                    metodoPagoSeleccionado = metodoPagoId; // Actualizar método de pago seleccionado
                    FormaDePagoMenu.setText(nombreMetodoPago); // Actualizar texto del menú
                });

                FormaDePagoMenu.getItems().add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de excepciones, se puede mejorar según el caso
        }
    }
*/
    private int obtenerNuevoDetalleId() {
        // Implementar la lógica para obtener un nuevo ID de detalle
        return 0; // Este es solo un ejemplo
    }

    
    @FXML
    public void eliminarElementoFactura(MouseEvent event) {
        // Obtener el índice del elemento seleccionado en la tabla
        int selectedIndex = Tablaview.getSelectionModel().getSelectedIndex();
        
        // Verificar si se ha seleccionado un elemento
        if (selectedIndex >= 0) {
            // Eliminar el elemento seleccionado de la tabla
            Tablaview.getItems().remove(selectedIndex);
            
            // Actualizar el total a pagar
            actualizarTotalAPagar();
        } else {
            System.out.println("Por favor, seleccione un elemento para eliminar.");
        }
    }


 // Métodos para obtener y guardar el número de factura desde/hacia la base de datos
    private int obtenerUltimoNumeroFacturaDesdeBD() {
        int ultimoNumeroFactura = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT MAX(factura_id) AS ultimo_numero FROM factura";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                ultimoNumeroFactura = resultSet.getInt("ultimo_numero");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción aquí según sea necesario
        }

        return ultimoNumeroFactura;
    }

    /*private void guardarNumeroFacturaEnBD(int numeroFactura) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO factura (factura_id) VALUES (" + numeroFactura + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    
    public double obtenerValorTotalFactura(String facturaId) {
        double valorTotal = 0.0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            String totalQuery = "SELECT SUM(valor_total) AS total FROM detalle_factura WHERE factura_id = ?";
            PreparedStatement totalStmt = connection.prepareStatement(totalQuery);
            totalStmt.setString(1, facturaId);

            ResultSet totalRs = totalStmt.executeQuery();
            if (totalRs.next()) {
                valorTotal = totalRs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valorTotal;
    }
}
    


    





