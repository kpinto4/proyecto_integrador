package application;

public class Producto {
    // Propiedades del producto
    private String referencia;
    private String descripcion;
    private String categoria;
    private String cantidad;
    private String valor;
    private String stock;

    // Constructor para inicializar un objeto Producto con sus propiedades
    public Producto(String referencia, String descripcion, String categoria, String cantidad, String valor, String stock) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.valor = valor;
        this.stock = stock;
    }

    // Método getter para obtener la referencia del producto
    public String getReferencia() {
        return referencia;
    }

    // Método setter para establecer la referencia del producto
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    // Método getter para obtener la descripción del producto
    public String getDescripcion() {
        return descripcion;
    }

    // Método setter para establecer la descripción del producto
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método getter para obtener la categoría del producto
    public String getCategoria() {
        return categoria;
    }

    // Método setter para establecer la categoría del producto
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método getter para obtener la cantidad del producto
    public String getCantidad() {
        return cantidad;
    }

    // Método setter para establecer la cantidad del producto
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    // Método getter para obtener el valor del producto
    public String getValor() {
        return valor;
    }

    // Método setter para establecer el valor del producto
    public void setValor(String valor) {
        this.valor = valor;
    }

    // Método getter para obtener el stock del producto
    public String getStock() {
        return stock;
    }

    // Método setter para establecer el stock del producto
    public void setStock(String stock) {
        this.stock = stock;
    }
}