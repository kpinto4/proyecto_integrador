package application;

/**
 * La clase DetalleFactura representa un detalle de factura en el sistema.
 * @author Kevin Santiago
 *
 */
public class DetalleFactura {
    private int facturaId;
    private int referenciaProducto;
    private String descripcion;
    private int cantidad;
    private double valorTotal;

    /**
     * Constructor para inicializar un objeto DetalleFactura con sus propiedades.
     * @param facturaId
     * @param referenciaProducto
     * @param descripcion
     * @param cantidad
     * @param valorTotal
     */
    public DetalleFactura(int facturaId, int referenciaProducto, String descripcion, int cantidad, double valorTotal) {
        this.facturaId = facturaId;
        this.referenciaProducto = referenciaProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
    }

    // Métodos getters para los atributos de la clase.

    /**
     * Este metodo obtiene el id de la factura a la que pertenece este detalle.
     * @return El id de la factura a la que pertenece este detalle.
     */
    public int getFacturaId() {
        return facturaId;
    }

    /**
     * Obtiene la referencia del producto asociado al detalle de factura.
     * @return La referencia del producto asociado al detalle de factura.
     */
    public int getReferenciaProducto() {
        return referenciaProducto;
    }

    /**
     * Obtiene la descripción del producto asociado al detalle de factura.
     * @return La descripción del producto asociado al detalle de factura.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la cantidad del producto en el detalle de factura.
     * @return La cantidad del producto en el detalle de factura.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene el valor total del detalle de factura.
     * @return El valor total del detalle de factura.
     */
    public double getValorTotal() {
        return valorTotal;
    }
}