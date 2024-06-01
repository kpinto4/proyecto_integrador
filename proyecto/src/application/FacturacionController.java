package application;

/**
 * Importa las clases necesarias.
 */
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

/**
 * Esta clase sirve para ver y manipular las acciones de la ventana Facturacion.
 * @author Kevin Santiago
 *
 */
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
        
        // Obtener el último número de factura desde la base de datos.
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
    
    /**
     * Configura los controladores de eventos para los elementos del menú relacionados con la selección de métodos de pago.
     * Este método establece acciones para seleccionar diferentes métodos de pago, como efectivo, tarjeta de crédito o tarjeta de débito.
     * Cuando se selecciona un método de pago, actualiza la interfaz de usuario para reflejar el método elegido.
     */
    private void configurarEventosMenuItem() {
        Efectivobtn.setOnAction(event -> {
            metodoPagoSeleccionado = 1;
            String nombreMetodoPago = "Efectivo";
            FormaDePagoMenu.setText(nombreMetodoPago);
        });

        TCreditobtn.setOnAction(event -> {
            metodoPagoSeleccionado = 2;
            String nombreMetodoPago = "Tarjeta de Crédito";
            FormaDePagoMenu.setText(nombreMetodoPago);
        });

        TDebitoBtn.setOnAction(event -> {
            metodoPagoSeleccionado = 3;
            String nombreMetodoPago = "Tarjeta de Débito";
            FormaDePagoMenu.setText(nombreMetodoPago);
        });
    }

    /**
     * Metodo para buscar un cliente en los datos de factura utilizando la cédula proporcionada.
     */
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

    /**
     * Metodo para agregar un producto ingresado a la factura.
     * @param event	El evento del mouse que desencadena el método.
     */
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

    /**
     * Actualiza el campo de texto del total a pagar en la interfaz de usuario.
     * Calcula el total sumando el valor de cada producto multiplicado por su cantidad
     * y luego establece este total en el campo de texto del total a pagar.
     */
    private void actualizarTotalAPagar() {
        double total = 0.0;
        ObservableList<Producto> productos = Tablaview.getItems();
        for (Producto producto : productos) {
            total += Double.parseDouble(producto.getValor()) * Integer.parseInt(producto.getStock());
        }
        totalPagoText.setText(String.valueOf(total));
    }

    /**
     * Este metodo se usa para llamar a la columna referencia.
     * @param event
     */
    @FXML
    public void ReferenciaColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    /**
     * Este metodo se usa para llamar a la columna descripción.
     * @param event
     */
    @FXML
    public void DescripcionColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    /**
     * Este metodo se usa para llamar a la columna cantidad.
     * @param event
     */
    @FXML
    public void CantidadColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    /**
     * Este metodo se usa para llamar a la columna valor.
     * @param event
     */
    @FXML
    public void ValorUColumn(ActionEvent event) {
        // TODO Autogenerated
    }

    /**
     * Método para cerrar la ventana actual y volver al menú principal.
     * @param event
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

    /**
     * Este metodo busca y muestra los detalles de una factura en respuesta a un evento de MouseClicked.
     * @param event El evento del Mouse que desencadena la llamada a este método.
     */
    @FXML
    public void buscarFactura(MouseEvent event) {
        // Obtener el ID de la factura ingresado en numeroFcaturaText
        int facturaIdBuscada = Integer.parseInt(numeroFcaturaText.getText().trim()); 
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM factura WHERE factura_id = ?")) {
            
            // Establecer el parámetro del ID de la factura para la consulta preparada
            statement.setInt(1, facturaIdBuscada);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los detalles de la factura
                int facturaId = resultSet.getInt("factura_id");
                Date fecha = resultSet.getDate("fecha");
                String cedulaCliente = resultSet.getString("cedula_cliente");
                int metodoPagoId = resultSet.getInt("metodo_pago_id");

                // Imprimir o procesar los datos de la factura
                System.out.println("\n Factura ID: " + facturaId);
                System.out.println("\n Fecha: " + fecha);
                System.out.println("\n Cedula Cliente: " + cedulaCliente);
                System.out.println("\n Metodo de Pago ID: " + metodoPagoId);
                
                // Obtener los detalles de la factura
                List<DetalleFactura> detalles = obtenerDetallesFactura(facturaId);
                for (DetalleFactura detalle : detalles) {
                    // Imprimir o procesar los detalles de la factura
                    System.out.println("\n Referencia Producto: " + detalle.getReferenciaProducto());
                    System.out.println("\n Descripcion: " + detalle.getDescripcion());
                    System.out.println("\n Cantidad: " + detalle.getCantidad());
                    System.out.println("\n Valor Total: " + detalle.getValorTotal());
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo que obtiene los detalles de la factura correspondiente al ID de factura proporcionado.
     * @param facturaId El ID de la factura de la cual queremos los detalles.
     * @return Una lista de objetos Detalle Factura que contiene los detalles de la factura
     * 
     */
    private List<DetalleFactura> obtenerDetallesFactura(int facturaId) {
        List<DetalleFactura> detalles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT referencia_producto, descripcion, cantidad, valor_total " +
                     "FROM detalle_factura " +
                     "WHERE factura_id = ?")) {
            
            // Establecer el parámetro para la consulta preparada
            statement.setInt(1, facturaId);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            
            // Iterar sobre los resultados y agregar cada detalle a la lista
            while (resultSet.next()) {
                int referenciaProducto = resultSet.getInt("referencia_producto");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double valorTotal = resultSet.getDouble("valor_total");
                
                // Crear un objeto DetalleFactura y agregarlo a la lista
                DetalleFactura detalle = new DetalleFactura(facturaId, referenciaProducto, descripcion, cantidad, valorTotal);
                detalles.add(detalle);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

	/**
	 * Método para imprimir los detalles de la factura en la consola
	 * @param factura La factura de la cual se desean imprimir los detalles.
	 */
    public void imprimirDetallesFactura(Factura factura) {
        // Mostrar información básica de la factura
    	System.out.println("mostrar factrua");

    }

    /**
     * Genera una nueva factura en la base de datos utilizando los datos proporcionados por el usuario.
     * Inserta la factura y sus detalles en las tablas correspondientes.
     * Realiza validaciones de los datos antes de la inserción y maneja transacciones para asegurar la integridad de los datos.
     */
    @FXML
    public void generarFactura() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
        	// Desactivar el modo de autoconfirmación para manejar la transacción manualmente
        	connection.setAutoCommit(false);

        	// Obtener los datos de la factura
            int facturaId = numeroFactura;
            Date fecha = new Date(System.currentTimeMillis());
            String cedulaCliente = cedulaClienteText.getText();
            int metodoPagoId = metodoPagoSeleccionado;

            // Validación de los datos antes de la inserción
            if (cedulaCliente == null || cedulaCliente.isEmpty()) {
                throw new SQLException("La cédula del cliente no puede estar vacía.");
            }
            if (metodoPagoId == 0) {
                throw new SQLException("Debe seleccionar un método de pago.");
            }

            // Insertar factura
            String sqlFactura = "INSERT INTO factura (factura_id, fecha, cedula_cliente, metodo_pago_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statementFactura = connection.prepareStatement(sqlFactura)) {
                statementFactura.setInt(1, facturaId);
                statementFactura.setDate(2, fecha);
                statementFactura.setString(3, cedulaCliente);
                statementFactura.setInt(4, metodoPagoId);
                statementFactura.executeUpdate();
            }

            // Insertar detalles de la factura
            ObservableList<Producto> productos = Tablaview.getItems();
            for (Producto producto : productos) {
                // Validación de datos del producto
                String referencia = producto.getReferencia();
                String descripcion = producto.getDescripcion();
                int cantidad = Integer.parseInt(producto.getStock());
                double valor = Double.parseDouble(producto.getValor());

                if (referencia == null || referencia.isEmpty() || descripcion == null || descripcion.isEmpty() || cantidad <= 0 || valor <= 0) {
                    throw new SQLException("Datos del producto inválidos: " + producto);
                }

                String sqlDetalle = "INSERT INTO detalle_factura (factura_id, referencia_producto, descripcion, cantidad, valor_total) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statementDetalle = connection.prepareStatement(sqlDetalle)) {
                    statementDetalle.setInt(1, facturaId);
                    statementDetalle.setString(2, referencia);
                    statementDetalle.setString(3, descripcion);
                    statementDetalle.setInt(4, cantidad);
                    statementDetalle.setDouble(5, valor * cantidad);
                    statementDetalle.executeUpdate();
                }
            }

            // Confirmar la transacción
            connection.commit();
            
            // Actualizar el número de factura y limpiar los campos de la interfaz de usuario
            numeroFactura++;
            numeroFcaturaText.setText(String.valueOf(numeroFactura));
            Tablaview.getItems().clear();
            totalPagoText.clear();
            cedulaClienteText.clear();
            nombreClienteText.clear();
            System.out.println("Factura generada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al generar la factura: " + e.getMessage());
        }
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
   /* private int obtenerNuevoDetalleId() {
        // Implementar la lógica para obtener un nuevo ID de detalle
        return 0; // Este es solo un ejemplo
    }*/

    

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
        int ultimoNumeroFactura = 1;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT MAX(factura_id) AS max_id FROM factura";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                if (maxId > 0) {
                    ultimoNumeroFactura = maxId + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoNumeroFactura;
    }


    
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
    


    





