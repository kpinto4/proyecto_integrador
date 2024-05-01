package application;

public class Producto {
    // Propiedades del producto
    private String codProducto;
    private String descripcion;
    private String categoria;
    private String valor;
    private String stock;

    // Constructor para inicializar un objeto Producto con sus propiedades
    public Producto(String codProducto, String descripcion, String categoria, String valor, String stock) {
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.valor = valor;
        this.stock = stock;
    }

    // Métodos getter y setter para codProducto
    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    // Métodos getter y setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos getter y setter para valor
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    // Métodos getter y setter para stock
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

       
}