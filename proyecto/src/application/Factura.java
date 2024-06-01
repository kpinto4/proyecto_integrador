package application;

import java.util.Date;
import java.util.List;

public class Factura {
    private int facturaId;
    private Date fecha;
    private String cedulaCliente;
    private int metodoPagoId;
    private List<DetalleFactura> detalles;

    public Factura(int facturaId, Date fecha, String cedulaCliente, int metodoPagoId, List<DetalleFactura> detalles) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.cedulaCliente = cedulaCliente;
        this.metodoPagoId = metodoPagoId;
        this.detalles = detalles;
    
    }

    public int getfacturaId() {
        return facturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }


    public int getMetodoPagoId() {
        return metodoPagoId;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }
}