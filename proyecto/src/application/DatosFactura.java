package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DatosFactura {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "BASE";
    private static final String CONTRASEÑA = "BASE";
    private static Connection conexion;
    

    private DatosFactura() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHelper {
        private static final DatosFactura INSTANCE = new DatosFactura();
    }

    public static DatosFactura getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String consulta = "SELECT * FROM producto";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
             ResultSet resultado = declaracion.executeQuery()) {
            while (resultado.next()) {
                Producto producto = new Producto(
                    resultado.getString("referencia"),
                    resultado.getString("descripcion"),
                    resultado.getString("categoria_id"),
                    resultado.getString("stock"),
                    resultado.getString("valor")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String consulta = "SELECT * FROM cliente";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
             ResultSet resultado = declaracion.executeQuery()) {
            while (resultado.next()) {
                Cliente cliente = new Cliente(
                    resultado.getString("Cedula"),
                    resultado.getString("Nombre"),
                    resultado.getString("Direccion"),
                    resultado.getString("Telefono")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
    
    public Cliente buscarClientePorCedula(String cedula) {
        Cliente cliente = null;
        String consulta = "SELECT * FROM cliente WHERE cedula = ?";

        try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
            declaracion.setString(1, cedula);
            ResultSet resultado = declaracion.executeQuery();

            if (resultado.next()) {
                cliente = new Cliente(
                    resultado.getString("cedula"),
                    resultado.getString("nombre"),
                    resultado.getString("direccion"),
                    resultado.getString("telefono")
                );
            } else {
                System.out.println("No se encontró ningún cliente con la cédula proporcionada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por cédula: " + e.getMessage());
        }

        return cliente;
    }

    public Connection getConnection() {
        return conexion;
    }

    public Factura buscarFacturaPorId(String facturaId) {
        Factura factura = null;

        try (Connection connection = getConnection()) {
            String facturaQuery = "SELECT f.factura_id, f.fecha, f.cedula_cliente, f.metodo_pago_id, m.nombre AS nombre " +
                                  "FROM factura f " +
                                  "JOIN metodo_pago m ON f.metodo_pago_id = m.metodo_id " +
                                  "WHERE f.factura_id = ?";
            PreparedStatement facturaStmt = connection.prepareStatement(facturaQuery);
            facturaStmt.setString(1, facturaId);

            ResultSet facturaRs = facturaStmt.executeQuery();
            if (facturaRs.next()) {
                int Id = facturaRs.getInt("factura_id");
                Date fecha = facturaRs.getDate("fecha");
                String cedulaCliente = facturaRs.getString("cedula_cliente");
                int metodoPagoId = facturaRs.getInt("metodo_pago_id");
               String metodoPagoNombre = facturaRs.getString("nombre");

                // Obtener detalles de la factura
                List<DetalleFactura> detalles = obtenerDetallesFactura(Id);

                factura = new Factura(Id, fecha, cedulaCliente, metodoPagoId, detalles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factura;
    }
    
    public List<DetalleFactura> obtenerDetallesFactura(int numeroFactura) {
        List<DetalleFactura> detalles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM detalle_factura WHERE factura_id = ?")) {
            statement.setInt(1, numeroFactura);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int detalleId = resultSet.getInt("detalle_id");
                int referenciaProducto = resultSet.getInt("referencia_producto");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double valorTotal = resultSet.getDouble("valor_total");
                
                DetalleFactura detalle = new DetalleFactura(numeroFactura, referenciaProducto, descripcion, cantidad, valorTotal);
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }
    
    public void generarFactura(Factura factura) {
        String sqlFactura = "INSERT INTO factura (factura_id, fecha, cedula_cliente, metodo_pago_id) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_factura (detalle_id, factura_id, referencia_producto, descripcion, cantidad, total) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
             PreparedStatement statementFactura = connection.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementDetalle = connection.prepareStatement(sqlDetalle)) {

            // Insertar la factura
            statementFactura.setInt(1, factura.getfacturaId());
            statementFactura.setDate(2, new java.sql.Date(factura.getFecha().getTime())); // Convertir la fecha a un java.sql.Date
            statementFactura.setString(3, factura.getCedulaCliente());
            statementFactura.setInt(4, factura.getMetodoPagoId());
            statementFactura.executeUpdate();

            // Obtener el ID de la factura generada
            ResultSet generatedKeys = statementFactura.getGeneratedKeys();
            int facturaId = -1;
            if (generatedKeys.next()) {
                facturaId = generatedKeys.getInt(1);
            }

            // Insertar los detalles de la factura
            for (DetalleFactura detalle : factura.getDetalles()) {
                statementDetalle.setInt(2, facturaId); // Usar el ID de la factura generada
                statementDetalle.setInt(3, detalle.getReferenciaProducto());
                statementDetalle.setString(4, detalle.getDescripcion());
                statementDetalle.setInt(5, detalle.getCantidad());
                statementDetalle.setDouble(6, detalle.getValorTotal());
                statementDetalle.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MetodoPago> obtenerMetodosPago() {
        List<MetodoPago> metodosPago = new ArrayList<>();
        String query = "SELECT * FROM metodo_pago";

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int metodoId = resultSet.getInt("metodo_id");
                String nombre = resultSet.getString("nombre");
                MetodoPago metodoPago = new MetodoPago(metodoId, nombre);
                metodosPago.add(metodoPago);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return metodosPago;
    }

    
    /*
    private void insertarDetalleFactura(int facturaId, Producto producto) {
        String insertDetalleSQL = "INSERT INTO detalle_factura (factura_id, referencia_producto, descripcion, cantidad, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement detalleStmt = connection.prepareStatement(insertDetalleSQL)) {

            detalleStmt.setInt(1, facturaId);
            detalleStmt.setInt(2, Integer.parseInt(producto.getReferencia()));
            detalleStmt.setString(3, producto.getDescripcion());
            detalleStmt.setInt(4, Integer.parseInt(producto.getStock()));
            detalleStmt.setDouble(5, Double.parseDouble(producto.getValor()) * Integer.parseInt(producto.getStock()));
            detalleStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    
}

