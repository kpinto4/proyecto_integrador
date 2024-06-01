package application;

import java.util.Date;
import java.util.List;

/**
 * La clase Factura representa una factura en el sistema.
 * @author Kevin Santiago
 *
 */
public class Factura {
    private int facturaId;
    private Date fecha;
    private String cedulaCliente;
    private int metodoPagoId;
    private List<DetalleFactura> detalles;

    /**
     * Constructor para inicializar un objeto Factura con sus propiedades.
     * @param facturaId
     * @param fecha
     * @param cedulaCliente
     * @param metodoPagoId
     * @param detalles
     */
    public Factura(int facturaId, Date fecha, String cedulaCliente, int metodoPagoId, List<DetalleFactura> detalles) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.cedulaCliente = cedulaCliente;
        this.metodoPagoId = metodoPagoId;
        this.detalles = detalles;
    
    }

    // Métodos getters para los atributos de la clase.
    
    
    /**
     * Este metodo obtiene el id de la factura.
     * @return El id de la factura.
     */
    public int getfacturaId() {
        return facturaId;
    }

    /**
     * Este metodo obtiene la fecha de la factura.
     * @return La fecha de la factura.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Este metodo obtiene la cédula del cliente asociado a la factura.
     * @return La cédula del cliente asociado a la factura.
     */
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    /**
     * Este metodo obtiene el id del método de pago utilizado en la factura.
     * @return El id del método de pago utilizado en la factura.
     */   
    public int getMetodoPagoId() {
        return metodoPagoId;
    }

    /**
     * Este metodo obtiene la lista de detalles de factura.
     * @return La lista de detalles de factura.
     */
    public List<DetalleFactura> getDetalles() {
        return detalles;
    }
}