package application;

public class DetalleFactura {
    private int detalleId;
    private int facturaId;
    private int referenciaProducto;
    private String descripcion;
    private int cantidad;
    private double valorTotal;

    public DetalleFactura(int detalleId, int facturaId, int referenciaProducto, String descripcion, int cantidad, double valorTotal) {
        this.detalleId = detalleId;
        this.facturaId = facturaId;
        this.referenciaProducto = referenciaProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
    }

    public int getDetalleId() {
        return detalleId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public int getReferenciaProducto() {
        return referenciaProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}