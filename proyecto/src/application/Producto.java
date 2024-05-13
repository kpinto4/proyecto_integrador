package application;

public class Producto {
    // Propiedades del producto
    private String referencia;
    private String descripcion;
    private String categoria;
    private String stock;
    private String valor;
    

    // Constructor para inicializar un objeto Producto con sus propiedades
    public Producto(String referencia, String descripcion, String categoria,String stock, String valor) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.stock = stock;
        this.valor = valor;
    }

    // Métodos getter y setter para codProducto
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    // Métodos getter y setter para stock
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    // Métodos getter y setter para valor
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

       
}