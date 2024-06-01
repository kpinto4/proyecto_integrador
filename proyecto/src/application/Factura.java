package application;

import java.util.Date;
import java.util.List;

public class Factura {
    private int id;
    private Date fecha;
    private String cedulaCliente;
    private int metodoPagoId;
    private List<DetalleFactura> detalles;

    public Factura(int id, Date fecha, String cedulaCliente, int metodoPagoId, List<DetalleFactura> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.cedulaCliente = cedulaCliente;
        this.metodoPagoId = metodoPagoId;
        this.detalles = detalles;
    
    }

    public int getId() {
        return id;
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