package application;

/**
 * - Importaciones necesarias para el desarrollo.
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * - Clase encargada de evitar que exista un choque de datos para los datos que ingresan en factura.
 * @author Kevin Santiago
 *
 */
public class DatosDetalleFac {
    private Connection connection;

    /**
     * - Constructor que recibe la conexión a la base de datos
     * @param connection
     */
    public DatosDetalleFac(Connection connection) {
        this.connection = connection;
    }

    /**
     * - Método para insertar un nuevo detalle de factura en la base de datos.
     * @param facturaId
     * @param referenciaProducto
     * @param descripcion
     * @param cantidad
     * @param valorTotal
     */
    public void insertarDetalleFactura(int facturaId, int referenciaProducto, String descripcion, int cantidad, BigDecimal valorTotal) {
        // Obtener el próximo valor de la secuencia DETALLE_ID_SEQUENCE
        int detalleId = obtenerSiguienteDetalleId();

        // Preparar la consulta de inserción
        String sql = "INSERT INTO detalle_factura (detalle_id, factura_id, referencia_producto, descripcion, cantidad, valor_total) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Establecer los valores de los parámetros
            statement.setInt(1, detalleId); // Asignar el valor de detalleId generado
            statement.setInt(2, facturaId);
            statement.setInt(3, referenciaProducto);
            statement.setString(4, descripcion);
            statement.setInt(5, cantidad);
            statement.setBigDecimal(6, valorTotal);

            // Ejecutar la consulta de inserción
            int filasInsertadas = statement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa en la tabla detalle_factura.");
            } else {
                System.out.println("No se pudo insertar en la tabla detalle_factura.");
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL que pueda ocurrir
            e.printStackTrace();
        }
    }

    /**
     * - Método ficticio para obtener el próximo valor de la secuencia DETALLE_ID_SEQUENCE.
     * @return
     */
    private int obtenerSiguienteDetalleId() {
        // Aquí puedes implementar la lógica para obtener el siguiente valor de la secuencia
        // Por ejemplo, podrías realizar una consulta SQL para obtener el valor de la secuencia
        // O podrías implementar alguna otra lógica para generar identificadores únicos
        // En este ejemplo, simplemente retornamos un valor ficticio
        return 1;
    }
}